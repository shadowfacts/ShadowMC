package net.shadowfacts.shadowmc.gui.component.button;

import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Function;

/**
 * @author shadowfacts
 */
public class GUIButtonEnum<E extends Enum> extends GUIButtonText {

	protected E value;
	protected Function<E, String> localizer;

	public GUIButtonEnum(int x, int y, int width, int height, E value, Function<E, String> localizer) {
		super(x, y, width, height, null, "");
		this.callback = this::handleClick;
		this.localizer = localizer;
		setValue(value);

	}

	private boolean handleClick(GUIButtonText button, MouseButton mouseButton) {
		setValue(EnumUtils.getNextValue(value));
		return true;
	}

	public void setValue(E value) {
		this.value = value;
		setText(localizer.apply(value));
	}
}
