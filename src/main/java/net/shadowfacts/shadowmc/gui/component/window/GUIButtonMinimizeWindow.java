package net.shadowfacts.shadowmc.gui.component.window;

import net.minecraft.client.renderer.GlStateManager;
import net.shadowfacts.shadowmc.gui.component.button.GUIButton;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public class GUIButtonMinimizeWindow extends GUIButton {

	protected GUIComponentWindow window;

	public GUIButtonMinimizeWindow(int x, int y, int width, int height, GUIComponentWindow window) {
		super(x, y, width, height);
		this.window = window;
		drawBackground = false;
	}

	@Override
	protected boolean handlePress(MouseButton button) {
		window.toggleMinimized();
		return true;
	}

	@Override
	protected void drawButton() {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 1, 0);
		if (!window.minimized) {
			drawText("-", x, y, Color.YELLOW);
		} else {
			drawText("+", x, y, Color.GREEN);
		}
		GlStateManager.popMatrix();
	}

}
