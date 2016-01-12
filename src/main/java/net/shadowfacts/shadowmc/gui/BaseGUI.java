package net.shadowfacts.shadowmc.gui;

import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public class BaseGUI extends AbstractGUI {

	protected AbstractGUI guiBeingDragged;

	public BaseGUI(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		Optional<AbstractGUI> gui = children.stream()
				.filter(AbstractGUI::isVisible)
				.filter(theGui -> theGui.isWithinBounds(mouseX, mouseY))
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? -1 : gui1.zLevel < gui2.zLevel ? 1 : 0)
				.findFirst();
		if (gui.isPresent()) {
			gui.get().handleMouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void handleMouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {
		children.stream()
				.forEach(gui -> gui.handleMouseClickAnywhere(mouseX, mouseY, button));
	}

	@Override
	public void handleMouseMove(int mouseX, int mouseY, MouseButton mouseButton) {
		if (guiBeingDragged != null) {
			guiBeingDragged.updatePosition(mouseX, mouseY);
		} else {
			Optional<AbstractGUI> gui = children.stream()
					.filter(AbstractGUI::isVisible)
					.filter(theGui -> theGui.isWithinBounds(mouseX, mouseY))
					.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? -1 : gui1.zLevel < gui2.zLevel ? 1 : 0)
					.findFirst();
			if (gui.isPresent()) {
				if (gui.get().isWithinMovableBounds(mouseX, mouseY)) {
					guiBeingDragged = gui.get();
					guiBeingDragged.updatePosition(mouseX, mouseY);
				} else {
					gui.get().handleMouseMove(mouseX, mouseY, mouseButton);
				}
			}
		}
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, MouseButton mouseButton) {
		guiBeingDragged = null;
		Optional<AbstractGUI> gui = children.stream()
				.filter(AbstractGUI::isVisible)
				.filter(theGui -> theGui.isWithinBounds(mouseX, mouseY))
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? -1 : gui1.zLevel < gui2.zLevel ? 1 : 0)
				.findFirst();
		if (gui.isPresent()) {
			gui.get().handleMouseReleased(mouseX, mouseY, mouseButton);
		}
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
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? -1 : gui1.zLevel < gui2.zLevel ? 1 : 0)
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
		Optional<AbstractGUI> gui = children.stream()
				.filter(theGui -> theGui.isWithinBounds(x, y))
				.sorted((gui1, gui2) -> gui1.zLevel > gui2.zLevel ? -1 : gui1.zLevel < gui2.zLevel ? 1 : 0)
				.findFirst();
		if (gui.isPresent()) {
			gui.get().drawTooltip(x, y);
		}
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

	@Override
	public void setZLevel(float zLevel) {
		super.setZLevel(zLevel);
		for (int i = 0; i < children.size(); i++) {
			children.get(i).setZLevel(zLevel + 1 + i);
		}
	}

}
