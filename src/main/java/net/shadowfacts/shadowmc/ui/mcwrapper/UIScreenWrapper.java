package net.shadowfacts.shadowmc.ui.mcwrapper;

import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ITickable;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.UIKeyInteractable;
import net.shadowfacts.shadowmc.ui.UIMouseInteractable;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author shadowfacts
 */
public class UIScreenWrapper extends GuiScreen {

	@Setter
	protected Set<UIKeyInteractable> keyHandlers = new LinkedHashSet<>();
	protected Set<UIElement> children = new LinkedHashSet<>();
	@Setter
	private Runnable updateHandler;
	@Setter
	private Runnable closeHandler;
	@Setter
	private boolean pausesGame;

	public UIScreenWrapper() {
		keyHandlers.add((keyCode, keyChar) -> {
			if (keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_E) {
				mc.displayGuiScreen(null);
			}
		});
	}

	public UIScreenWrapper add(UIElement element) {
		element.setInvalidationHandler(this::layout);
		children.add(element);
		return this;
	}

	public UIScreenWrapper layout() {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();

		for (UIElement e : children) {
			int marginLeft = e.getStyle(UIAttribute.MARGIN_LEFT);
			int marginRight = e.getStyle(UIAttribute.MARGIN_RIGHT);
			int marginTop = e.getStyle(UIAttribute.MARGIN_TOP);
			int marginBottom = e.getStyle(UIAttribute.MARGIN_BOTTOM);
			e.layout(marginLeft, screenWidth - marginRight, marginTop, screenHeight - marginBottom);
		}

		return this;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return pausesGame;
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		if (closeHandler != null) {
			closeHandler.run();
		}
	}

	@Override
	public void onResize(Minecraft mc, int w, int h) {
		super.onResize(mc, w, h);

		layout();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		children.forEach(e -> e.draw(mouseX, mouseY));
		children.forEach(e -> e.drawTooltip(mouseX, mouseY));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		if (updateHandler != null) {
			updateHandler.run();
		}

		children.stream()
				.filter(e -> e instanceof ITickable)
				.map(e -> (ITickable)e)
				.forEach(ITickable::update);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		MouseButton button = MouseButton.get(mouseButton);
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.peek(e -> ((UIMouseInteractable)e).mouseClickAnywhere(mouseX, mouseY, button))
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseClickDown(mouseX, mouseY, button));
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);

		MouseButton button = MouseButton.get(state);
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseClickUp(mouseX, mouseY, button));
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

		MouseButton button = MouseButton.get(clickedMouseButton);

		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseMove(mouseX, mouseY, button, timeSinceLastClick));
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		int mouseX = Mouse.getEventX() * width / mc.displayWidth;
		int mouseY = height - Mouse.getEventY() * height / mc.displayHeight - 1;

		int delta = Mouse.getDWheel();
		if (delta != 0) {
			children.stream()
					.filter(e -> e instanceof UIMouseInteractable)
					.map(e -> (UIMouseInteractable)e)
					.forEach(e -> e.mouseScroll(mouseX, mouseY, delta));
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);

		keyHandlers.forEach(handler -> handler.keyPress(keyCode, typedChar));
		children.stream()
				.filter(e -> e instanceof UIKeyInteractable)
				.map(e -> (UIKeyInteractable)e)
				.forEach(e -> e.keyPress(keyCode, typedChar));
	}

}
