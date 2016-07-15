package net.shadowfacts.shadowmc.ui.element.textfield;

import java.util.function.IntConsumer;
import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
public class UIIntegerField extends UITextField {

	private IntConsumer handler;

	private int min;
	private int max;

	public UIIntegerField(IntConsumer handler, int value, int min, int max, String id, String... classes) {
		super(Integer.toString(value), Pattern.compile("-?\\d+"), null, id, classes);
		this.handler = handler;
		this.min = min;
		this.max = max;
	}

	public UIIntegerField(IntConsumer handler, int value, String id, String... classes) {
		this(handler, value, Integer.MIN_VALUE, Integer.MAX_VALUE, id, classes);
	}

	@Override
	protected void handleChange() {
		if (text.isEmpty()) text = "0";
		long val = Long.parseLong(text);
		if (val < min) {
			text = Integer.toString(min);
		} else if (val > max) {
			text = Integer.toString(max);
		}
		handler.accept(Integer.parseInt(text));
	}

}
