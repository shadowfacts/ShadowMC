package net.shadowfacts.shadowmc.gui.component.button;

import lombok.Getter;
import lombok.Setter;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.BiFunction;

/**
 * @author shadowfacts
 */
public class GUIButtonText extends GUIButton {

	@Getter @Setter
	protected String text;

	protected Color color = Color.WHITE;

	protected BiFunction<GUIButtonText, MouseButton, Boolean> callback;

	public GUIButtonText(int x, int y, int width, int height, BiFunction<GUIButtonText, MouseButton, Boolean> callback, String text) {
		super(x, y, width, height);
		this.callback = callback;
		this.text = text;
	}

	@Override
	protected boolean handlePress(MouseButton button) {
		return callback.apply(this, button);
	}

	@Override
	protected void drawButton() {
		drawCenteredText(text, x, x + width, y, y + height, color);
	}

	public GUIButtonText setColor(Color color) {
		this.color = color;
		return this;
	}

}
