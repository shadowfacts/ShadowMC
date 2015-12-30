package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.shadowfacts.shadowmc.gui.BaseGUI;

import java.util.List;

/**
 * @author shadowfacts
 */
public class MCGUIWrapper extends BaseGUI {

	private MCGui mcGUI;

	public MCGUIWrapper(MCGui mcGUI) {
		super(mcGUI.mc, 0, 0, mcGUI.width, mcGUI.height);
		this.mcGUI = mcGUI;
	}

	public void draw(int mouseX, int mouseY) {
		draw();
		drawTooltip(mouseX, mouseY);
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		mcGUI.drawHoveringText(text, x, y);
	}

	@Override
	public void handleKeyPress(int key, char charTyped) {
		super.handleKeyPress(key, charTyped);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, int mouseButton) {
		super.handleMouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, int releasedButton) {

	}

}
