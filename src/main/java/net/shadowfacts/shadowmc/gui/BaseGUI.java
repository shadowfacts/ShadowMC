package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.Minecraft;

/**
 * @author shadowfacts
 */
public class BaseGUI extends AbstractInteractiveGUI {

	public BaseGUI(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	@Override
	public void init() {
		getChildren().stream()
				.forEach(AbstractGUI::init);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, int mouseButton) {
		getChildren().stream()
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.filter(gui -> gui instanceof AbstractInteractiveGUI)
				.map(gui -> (AbstractInteractiveGUI)gui)
				.forEach(gui -> gui.handleMouseClicked(mouseX, mouseY, mouseButton));
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, int mouseButton) {
		getChildren().stream()
				.filter(gui -> gui.isWithinBounds(mouseX, mouseY))
				.filter(gui -> gui instanceof AbstractInteractiveGUI)
				.map(gui -> (AbstractInteractiveGUI)gui)
				.forEach(gui -> gui.handleMouseReleased(mouseX, mouseY, mouseButton));
	}

	@Override
	public void handleKeyPress(int key, char charTyped) {
		getChildren().stream()
				.filter(gui -> gui instanceof AbstractInteractiveGUI)
				.map(gui -> (AbstractInteractiveGUI)gui)
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
