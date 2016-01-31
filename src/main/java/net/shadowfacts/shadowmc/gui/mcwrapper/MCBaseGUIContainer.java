package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.minecraft.client.renderer.GlStateManager;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.gui.handler.ExitWindowKeyHandler;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author shadowfacts
 */
public class MCBaseGUIContainer extends BaseGUI {

	protected GuiContainerWrapper wrapper;

	public MCBaseGUIContainer(GuiContainerWrapper wrapper) {
		super(0, 0, wrapper.width, wrapper.height);
		this.wrapper = wrapper;

		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_ESCAPE));
		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_E));
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		wrapper.drawHoveringText(text, x, y);
	}

}
