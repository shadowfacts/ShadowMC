package net.shadowfacts.shadowmc.gui.component.window;

import net.shadowfacts.shadowmc.gui.component.button.GUIButton;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public class GUIButtonCloseWindow extends GUIButton {

	protected GUIComponentWindow window;

	public GUIButtonCloseWindow(int x, int y, int width, int height, GUIComponentWindow window) {
		super(x, y, width, height);
		this.window = window;
		drawBackground = false;
	}

	@Override
	protected boolean handlePress(MouseButton button) {
		window.close();
		return true;
	}

	@Override
	protected void drawButton() {
		drawText("x", x, y, Color.PURE_RED);
	}

}
