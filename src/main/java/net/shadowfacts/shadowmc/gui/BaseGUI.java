package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public class BaseGUI extends AbstractGUI {

	public BaseGUI(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	public BaseGUI(Minecraft mc) {
		this(mc, 0, 0, mc.displayWidth, mc.displayHeight);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		children.stream()
				.filter(AbstractGUI::isVisible)
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.forEach(gui -> gui.handleMouseClicked(mouseX, mouseY, button));
	}

	@Override
	public void handleMouseMove(int mouseX, int mouseY, MouseButton mouseButton) {
		Optional<AbstractGUI> gui = children.stream()
				.filter(AbstractGUI::isVisible)
				.filter(theGui -> theGui.isWithinBounds(mouseX, mouseY))
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? 1 : gui1.zLevel < gui2.zLevel ? -1 : 0)
				.findFirst();
		if (gui.isPresent()) {
			if (gui.get().movable) {
				gui.get().updatePosition(mouseX, mouseY);
			} else {
				gui.get().handleMouseMove(mouseX, mouseY, mouseButton);
			}
		}
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, MouseButton mouseButton) {
		children.stream()
				.filter(AbstractGUI::isVisible)
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.forEach(gui -> gui.handleMouseReleased(mouseX, mouseY, mouseButton));
	}

	@Override
	public void handleKeyPress(int key, char charTyped) {
		children.stream()
				.forEach(gui -> gui.handleKeyPress(key, charTyped));
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		children.stream()
				.filter(AbstractGUI::isVisible)
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? 1 : gui1.zLevel < gui2.zLevel ? -1 : 0)
				.forEach(gui -> gui.draw(mouseX, mouseY));
	}

	@Override
	public void update() {
		children.stream()
				.filter(AbstractGUI::isVisible)
				.forEach(AbstractGUI::update);
	}

	@Override
	public void drawTooltip(int x, int y) {
		children.stream()
				.filter(gui -> gui.isWithinBounds(x, y))
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? 1 : gui1.zLevel < gui2.zLevel ? -1 : 0)
				.forEach(gui -> gui.drawTooltip(x, y));
	}

	@Override
	public void updatePosition(int newX, int newY) {
		children.stream()
				.forEach(gui -> {
					gui.x = gui.x - x + newX;
					gui.y = gui.y - y + newY;
				});
		super.updatePosition(newX, newY);
	}

}
