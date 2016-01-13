package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.gui.handler.ExitWindowKeyHandler;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author shadowfacts
 */
public class MCBaseGUI extends BaseGUI {

	protected GuiScreenWrapper mcGUI;

	public MCBaseGUI(GuiScreenWrapper mcGUI) {
		super(0, 0, mcGUI.width, mcGUI.height);
		this.mcGUI = mcGUI;

		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_ESCAPE));
		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_E));
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		mcGUI.drawHoveringText(text, x, y);
	}

}
