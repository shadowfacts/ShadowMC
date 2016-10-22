package net.shadowfacts.shadowmc.ui.style;

import lombok.Getter;
import net.shadowfacts.shadowmc.ui.util.factory.BooleanFactory;
import net.shadowfacts.shadowmc.ui.util.factory.ColorFactory;
import net.shadowfacts.shadowmc.ui.util.factory.IntegerFactory;
import net.shadowfacts.shadowmc.ui.util.factory.ValueFactory;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author shadowfacts
 */
public class UIAttribute<T> {

	private static final Map<String, UIAttribute<?>> attributes = new HashMap<>();
	private static final Map<UIAttribute<?>, ValueFactory<?>> valueFactories = new HashMap<>();

	public static final UIAttribute<UILayoutMode> LAYOUT = new UIAttribute<>("layout", UILayoutMode.PREFERRED, UILayoutMode.FACTORY);

	public static final UIAttribute<UIHorizontalLayoutMode> HORIZONTAL_LAYOUT = new UIAttribute<>("horizontal-layout", UIHorizontalLayoutMode.CENTER, UIHorizontalLayoutMode.FACTORY);
	public static final UIAttribute<UIVerticalLayoutMode> VERTICAL_LAYOUT = new UIAttribute<>("vertical-layout", UIVerticalLayoutMode.CENTER, UIVerticalLayoutMode.FACTORY);

	public static final UIAttribute<Integer> MARGIN_TOP = new UIAttribute<>("margin-top", 0, IntegerFactory.INSTANCE);
	public static final UIAttribute<Integer> MARGIN_BOTTOM = new UIAttribute<>("margin-bottom", 0, IntegerFactory.INSTANCE);
	public static final UIAttribute<Integer> MARGIN_LEFT = new UIAttribute<>("margin-left", 0, IntegerFactory.INSTANCE);
	public static final UIAttribute<Integer> MARGIN_RIGHT = new UIAttribute<>("margin-right", 0, IntegerFactory.INSTANCE);

	public static final UIAttribute<UIHorizontalLayoutMode> TEXT_HORIZONTAL_LAYOUT = new UIAttribute<>("text-horizontal-layout", UIHorizontalLayoutMode.LEFT, UIHorizontalLayoutMode.FACTORY);
	public static final UIAttribute<UIVerticalLayoutMode> TEXT_VERTICAL_LAYOUT = new UIAttribute<>("text-vertical-layout", UIVerticalLayoutMode.TOP, UIVerticalLayoutMode.FACTORY);
	public static final UIAttribute<Color> TEXT_COLOR = new UIAttribute<>("text-color", Color.WHITE, ColorFactory.INSTANCE);
	public static final UIAttribute<Boolean> TEXT_UNDERLINE = new UIAttribute<>("text-underline", false, BooleanFactory.INSTANCE);
	public static final UIAttribute<Boolean> TEXT_ITALIC = new UIAttribute<>("text-italic", false, BooleanFactory.INSTANCE);
	public static final UIAttribute<Boolean> TEXT_BOLD = new UIAttribute<>("text-bold", false, BooleanFactory.INSTANCE);
	public static final UIAttribute<Boolean> TEXT_SHADOW = new UIAttribute<>("text-shadow", false, BooleanFactory.INSTANCE);

	public static final UIAttribute<UIOrientation> ORIENTATION = new UIAttribute<>("orientation", UIOrientation.VERTICAL, UIOrientation.FACTORY);

	public static final UIAttribute<Integer> STACK_SPACING = new UIAttribute<>("stack-spacing", 0, IntegerFactory.INSTANCE);

	public static final UIAttribute<Boolean> BACKGROUND_ENABLED = new UIAttribute<>("background-enabled", true, BooleanFactory.INSTANCE);

	public static final UIAttribute<Color> PRIMARY_COLOR = new UIAttribute<>("primary-color", Color.RED, ColorFactory.INSTANCE);
	public static final UIAttribute<Color> SECONDARY_COLOR = new UIAttribute<>("secondary-color", new Color(0xAA0000), ColorFactory.INSTANCE);

	public static final UIAttribute<Color> BORDER_COLOR = new UIAttribute<>("border-color", new Color(0x373737), ColorFactory.INSTANCE);

	public static final UIAttribute<Integer> SCROLLBAR_WIDTH = new UIAttribute<>("scrollbar-width", 6, IntegerFactory.INSTANCE);
	public static final UIAttribute<Color> SCROLLBAR_BACKGROUND_COLOR = new UIAttribute<>("scrollbar-background-color", Color.BLACK, ColorFactory.INSTANCE);
	public static final UIAttribute<Color> SCROLLBAR_PRIMARY_COLOR = new UIAttribute<>("scrollbar-primary-color", new Color(0xC0C0C0), ColorFactory.INSTANCE);
	public static final UIAttribute<Color> SCROLLBAR_SCEONDARY_COLOR = new UIAttribute<>("scrollbar-secondary-color", new Color(0x808080), ColorFactory.INSTANCE);

	public static final UIAttribute<Color> TEXTFIELD_ENABLED_COLOR = new UIAttribute<>("textfield-enabled-color", new Color(14737632), ColorFactory.INSTANCE);
	public static final UIAttribute<Color> TEXTFIELD_DISABLED_COLOR = new UIAttribute<>("textfield-disabled-color", new Color(7368816), ColorFactory.INSTANCE);
	public static final UIAttribute<Color> TEXTFIELD_BACKGROUND_COLOR = new UIAttribute<>("textfield-background-color", Color.BLACK, ColorFactory.INSTANCE);
	public static final UIAttribute<Color> TEXTFIELD_BORDER_COLOR = new UIAttribute<>("textfield-border-color", new Color(-6250336), ColorFactory.INSTANCE);
	public static final UIAttribute<Integer> TEXTFIELD_BORDER_WIDTH = new UIAttribute<>("textfield-border-width", 1, IntegerFactory.INSTANCE);
	public static final UIAttribute<Color> TEXTFIELD_CURSOR_COLOR = new UIAttribute<>("textfield-cursor-color", new Color(-3092272), ColorFactory.INSTANCE);
	public static final UIAttribute<Color> TEXTFIELD_SELECTION_COLOR = new UIAttribute<>("textfield-selection-color", Color.BLUE, ColorFactory.INSTANCE);
	public static final UIAttribute<Boolean> TEXTFIELD_SHADOW = new UIAttribute<>("textfield-shadow", true, BooleanFactory.INSTANCE);

	@Getter
	private final T defaultVal;

	public UIAttribute(String name, T defaultVal, ValueFactory<T> valueFactory) {
		this.defaultVal = defaultVal;

		attributes.put(name, this);
		valueFactories.put(this, valueFactory);
	}

	@SuppressWarnings("unchecked")
	public static <T> UIAttribute<T> get(String name) {
		return (UIAttribute<T>)attributes.get(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> Function<String, T> getValueFactory(UIAttribute<T> attr) {
		ValueFactory<T> factory = (ValueFactory<T>)valueFactories.get(attr);
		return (s) -> factory.create(s, attr.defaultVal);
	}

}
