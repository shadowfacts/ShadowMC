package net.shadowfacts.shadowmc.gui.component.textfield;

import java.util.function.IntConsumer;
import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
public class GUIIntField extends GUITextField {

	private IntConsumer handler;

	private int min;
	private int max;

	public GUIIntField(int x, int y, int width, int height, int value, int min, int max, IntConsumer handler) {
		super(x, y, width, height, Integer.toString(value), Pattern.compile("-?\\d+"), null);
		this.handler = handler;
		this.min = min;
		this.max = max;
	}

	public GUIIntField(int x, int y, int width, int height, int value, IntConsumer handler) {
		this(x, y, width, height, value, Integer.MIN_VALUE, Integer.MAX_VALUE, handler);
	}

	@Override
	protected void handleChange() {
		if (text.isEmpty()) text = "0";
		if (Long.parseLong(text) < min) {
			text = Integer.toString(min);
		}
		if (Long.parseLong(text) > max) {
			text = Integer.toString(max);
		}
		handler.accept(Integer.parseInt(text));
	}

}
