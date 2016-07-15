package net.shadowfacts.shadowmc.ui.style;

import net.shadowfacts.shadowmc.ui.util.factory.ValueFactory;

/**
 * @author shadowfacts
 */
public enum UILayoutMode {

	MIN,
	PREFERRED,
	MAX;

	private static final String[] NAMES = {"min", "preferred", "max"};
	private static final UILayoutMode[] VALUES = values();

	public static final ValueFactory<UILayoutMode> FACTORY = (s, defaultVal) -> {
		for (int i = 0; i < NAMES.length; i++) {
			if (NAMES[i].equalsIgnoreCase(s)) {
				return VALUES[i];
			}
		}
		return defaultVal;
	};

}
