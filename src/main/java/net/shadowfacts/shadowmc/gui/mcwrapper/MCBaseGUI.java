package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.List;

/**
 * @author shadowfacts
 */
public class MCBaseGUI extends BaseGUI {

	private GuiScreenWrapper mcGUI;

	public MCBaseGUI(GuiScreenWrapper mcGUI) {
		super(mcGUI.mc, 0, 0, mcGUI.width, mcGUI.height);
		this.mcGUI = mcGUI;
	}

	public void drawAll(int mouseX, int mouseY) {
		draw(mouseX, mouseY);
		drawTooltip(mouseX, mouseY);
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		mcGUI.drawHoveringText(text, x, y);
	}

}
