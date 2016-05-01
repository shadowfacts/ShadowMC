package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GuiScreenWrapper extends GuiScreen {

	public MCBaseGUI gui;

	public boolean pausesGame = false;

	public boolean drawMCBackgroundOverlay = true;

	public GuiScreenWrapper() {
		this.gui = new MCBaseGUI(this);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (drawMCBackgroundOverlay && mc.theWorld != null) {
			drawGradientRect(0, 0, width, height, -1072689136, -804253680);
		} else {
			drawBackground(0);
		}
		gui.draw(mouseX, mouseY, partialTicks);
		gui.drawTooltip(mouseX, mouseY);
	}

	@Override
	public void updateScreen() {
		gui.update();
	}

	@Override
	protected void keyTyped(char charTyped, int keyCode) throws IOException {
		gui.handleKeyPress(keyCode, charTyped);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
		gui.handleMouseClicked(mouseX, mouseY, MouseButton.get(button));
		gui.handleMouseClickAnywhere(mouseX, mouseY, MouseButton.get(button));
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int button) {
		gui.handleMouseReleased(mouseX, mouseY, MouseButton.get(button));
	}

	@Override
	public void drawHoveringText(List<String> text, int x, int y) {
		drawHoveringText(text, x, y, mc.fontRendererObj);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return pausesGame;
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		gui.handleMouseMove(mouseX, mouseY, MouseButton.get(clickedMouseButton));
	}

	@Override
	public void setGuiSize(int width, int height) {
		super.setGuiSize(width, height);
		gui.setWidth(width);
		gui.setHeight(height);
		gui.setInitialized(true);
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		super.setWorldAndResolution(mc, width, height);
		gui.setWidth(width);
		gui.setHeight(height);
		gui.setInitialized(true);
	}

	@Override
	public void onGuiClosed() {
		gui.onGUIClosed();
	}
}
