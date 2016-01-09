package net.shadowfacts.shadowmc.gui.component.button;

import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class GUIButtonToggle extends GUIButton {

	protected Consumer<GUIButtonToggle> callback;

	public boolean state;

	public GUIButtonToggle(int x, int y, boolean state, Consumer<GUIButtonToggle> callback) {
		super(x, y, 20, 20);
		this.state = state;
		this.callback = callback;
	}

	public GUIButtonToggle(int x, int y, Consumer<GUIButtonToggle> callback) {
		this(x, y, true, callback);
	}

	@Override
	protected boolean handlePress(MouseButton button) {
		state = !state;
		callback.accept(this);
		return true;
	}

	@Override
	protected void drawButton() {
		bindTexture(widgetTextures);
		if (state) {
			drawTexturedRect(x, y, 0, 0, width, height);
		} else {
			drawTexturedRect(x, y, 20, 0, width, height);
		}
	}

}
