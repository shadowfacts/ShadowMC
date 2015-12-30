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

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		getChildren().stream()
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.forEach(gui -> gui.handleMouseClicked(mouseX, mouseY, button));
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, MouseButton mouseButton) {
		getChildren().stream()
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.forEach(gui -> gui.handleMouseReleased(mouseX, mouseY, mouseButton));
	}

	@Override
	public void handleKeyPress(int key, char charTyped) {
		getChildren().stream()
				.forEach(gui -> gui.handleKeyPress(key, charTyped));
	}

	@Override
	public void draw() {
		getChildren().stream()
				.filter(AbstractGUI::isVisible)
				.forEach(AbstractGUI::draw);
	}

	@Override
	public void update() {
		getChildren().stream()
				.filter(AbstractGUI::isVisible)
				.forEach(AbstractGUI::update);
	}

	public void drawTooltips(int mouseX, int mouseY) {
		getChildren().stream()
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.forEach(gui -> gui.drawTooltip(mouseX, mouseY));
	}

}
