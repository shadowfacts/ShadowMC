package net.shadowfacts.shadowmc.ui.element.button;

import lombok.Getter;
import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author shadowfacts
 */
public class UIButtonEnum<E extends Enum> extends UIButtonText {

	@Getter
	protected E value;
	protected Function<E, String> localizer;
	protected Consumer<UIButtonEnum<E>> clickHandler;

	public UIButtonEnum(E value, Function<E, String> localizer, Consumer<UIButtonEnum<E>> clickHandler, String id, String... classes) {
		super(localizer.apply(value), null, id, classes);
		this.value = value;
		this.callback = this::handleClick;
		this.localizer = localizer;
		this.clickHandler = clickHandler;
	}

	public UIButtonEnum(E value, Function<E, String> localizer, String id, String... classes) {
		this(value, localizer, null, id, classes);
	}

	public void setValue(E value) {
		this.value = value;
		setText(localizer.apply(value));
	}

	private boolean handleClick(UIButtonText button, MouseButton mouseButton) {
		if (mouseButton == MouseButton.LEFT) {
			setValue(EnumUtils.getNextValue(value));
		} else if (mouseButton == MouseButton.RIGHT) {
			setValue(EnumUtils.getPreviousValue(value));
		}
		if (clickHandler != null) clickHandler.accept(this);
		return true;
	}

}
