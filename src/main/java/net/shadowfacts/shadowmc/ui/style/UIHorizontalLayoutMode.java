package net.shadowfacts.shadowmc.ui.style;

import net.shadowfacts.shadowmc.ui.util.factory.ValueFactory;

/**
 * @author shadowfacts
 */
public enum UIHorizontalLayoutMode {

	LEFT,
	CENTER,
	RIGHT;

	private static final String[] NAMES = {"left", "center", "right"};

	public static final ValueFactory<UIHorizontalLayoutMode> FACTORY = (s, defaultVal) -> {
		for (int i = 0; i < NAMES.length; i++) {
			if (NAMES[i].equalsIgnoreCase(s)) {
				return values()[i];
			}
		}
		return defaultVal;
	};

}
