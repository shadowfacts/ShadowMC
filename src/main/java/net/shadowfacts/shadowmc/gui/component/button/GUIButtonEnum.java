package net.shadowfacts.shadowmc.gui.component.button;

import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author shadowfacts
 */
public class GUIButtonEnum<E extends Enum> extends GUIButtonText {

	protected E value;
	protected Function<E, String> localizer;
	protected Consumer<GUIButtonEnum<E>> clickHandler;

	public GUIButtonEnum(int x, int y, int width, int height, E value, Function<E, String> localizer, Consumer<GUIButtonEnum<E>> clickHandler) {
		super(x, y, width, height, null, "");
		this.callback = this::handleClick;
		this.localizer = localizer;
		this.clickHandler = clickHandler;
		setValue(value);
	}

	public GUIButtonEnum(int x, int y, int width, int height, E value, Function<E, String> localizer) {
		this(x, y, width, height, value, localizer, null);
	}

	private boolean handleClick(GUIButtonText button, MouseButton mouseButton) {
		setValue(EnumUtils.getNextValue(value));
		if (clickHandler != null) clickHandler.accept(this);
		return true;
	}

	public void setValue(E value) {
		this.value = value;
		setText(localizer.apply(value));
	}

	public GUIButtonEnum<E> setClickHandler(Consumer<GUIButtonEnum<E>> clickHandler) {
		this.clickHandler = clickHandler;
		return this;
	}
}
