package net.shadowfacts.shadowmc.gui.mcwrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.io.IOException;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GuiContainerWrapper extends GuiContainer {

	public BaseGUI gui;

	public boolean drawMCBackgroundOverlay = true;

	public GuiContainerWrapper(Container container) {
		super(container);
	}

	@Override
	public void drawWorldBackground(int tint) {
		if (drawMCBackgroundOverlay && mc.theWorld != null) {
			drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		} else {
			drawBackground(tint);
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		gui.update();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		MouseButton button = MouseButton.get(mouseButton);
		gui.handleMouseClicked(mouseX, mouseY, button);
		gui.handleMouseClickAnywhere(mouseX, mouseY, button);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int button) {
		super.mouseReleased(mouseX, mouseY, button);

		gui.handleMouseReleased(mouseX, mouseY, MouseButton.get(button));
	}

	@Override
	public void drawHoveringText(List<String> textLines, int x, int y) {
		super.drawHoveringText(textLines, x, y);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

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
		super.onGuiClosed();

		gui.onGUIClosed();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		gui.drawTooltip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		gui.draw(mouseX, mouseY, partialTicks);
	}

}
