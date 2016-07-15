package net.shadowfacts.shadowmc.ui.style;

import net.shadowfacts.shadowmc.ui.util.factory.ValueFactory;

/**
 * @author shadowfacts
 */
public enum UIVerticalLayoutMode {

	TOP,
	CENTER,
	BOTTOM;

	private static final String[] NAMES = {"top", "center", "bottom"};

	public static final ValueFactory<UIVerticalLayoutMode> FACTORY = (s, defaultVal) -> {
		for (int i = 0; i < NAMES.length; i++) {
			if (NAMES[i].equalsIgnoreCase(s)) {
				return values()[i];
			}
		}
		return defaultVal;
	};

}
