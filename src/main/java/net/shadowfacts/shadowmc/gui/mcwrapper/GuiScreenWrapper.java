package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.AbstractGUI;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GuiScreenWrapper extends GuiScreen {

	private MCGUIWrapper gui;

	public GuiScreenWrapper(AbstractGUI gui) {
		this.gui = new MCGUIWrapper(this);
		gui.setParent(this.gui);
		this.gui.addChild(gui);
		this.gui.init();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		gui.draw(mouseX, mouseY);
	}

	@Override
	public void updateScreen() {
		gui.update();
	}

	@Override
	protected void keyTyped(char charTyped, int keyCode) throws IOException {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(null);
		}
		gui.handleKeyPress(keyCode, charTyped);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		gui.handleMouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int releasedButton) {
		gui.handleMouseReleased(mouseX, mouseY, releasedButton);
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		drawHoveringText(text, x, y, mc.fontRendererObj);
	}

}
