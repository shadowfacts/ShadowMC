package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.AbstractGUI;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GuiScreenWrapper extends GuiScreen {

	private MCBaseGUI gui;

	public GuiScreenWrapper(AbstractGUI gui) {
		this.gui = new MCBaseGUI(this);
		gui.setParent(this.gui);
		this.gui.addChild(gui);
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
	protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
		gui.handleMouseClicked(mouseX, mouseY, MouseButton.get(button));
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int button) {
		gui.handleMouseReleased(mouseX, mouseY, MouseButton.get(button));
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		drawHoveringText(text, x, y, mc.fontRendererObj);
	}

}
