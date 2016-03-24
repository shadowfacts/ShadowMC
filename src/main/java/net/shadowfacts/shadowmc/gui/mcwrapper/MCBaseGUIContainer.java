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

	protected int guiLeft;
	protected int guiTop;

	public MCBaseGUIContainer(GuiContainerWrapper wrapper) {
		super(0, 0, wrapper.width, wrapper.height);
		this.wrapper = wrapper;

		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_ESCAPE));
		keyHandlers.add(new ExitWindowKeyHandler(Keyboard.KEY_E));
	}

	@Override
	public void setInitialized(boolean initialized) {
		super.setInitialized(initialized);
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		updatePosition(guiLeft, guiTop);
	}

	@Override
	public void drawTooltip(int x, int y) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(-guiLeft, -guiTop, 0);
		super.drawTooltip(x, y);
		GlStateManager.popMatrix();
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		wrapper.drawHoveringText(text, x, y);
	}

}
