package net.shadowfacts.shadowmc.gui.handler;

import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
public class ExitWindowKeyHandler implements KeyHandler {

	private int keyCode;

	@Override
	public void handleKeyPress(int keyCode, char charTyped) {
		Minecraft.getMinecraft().displayGuiScreen(null);
	}

	@Override
	public boolean acceptsKey(int keyCode, char charTyped) {
		return keyCode == this.keyCode;
	}

}
