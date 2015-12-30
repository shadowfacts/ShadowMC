package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.MouseButton;

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
				.forEach(gui -> gui.drawTooltip(x, y));
	}

}
