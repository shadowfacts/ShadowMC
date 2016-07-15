package net.shadowfacts.shadowmc.ui.style;

import net.shadowfacts.shadowmc.ui.util.factory.ValueFactory;

/**
 * @author shadowfacts
 */
public enum UIOrientation {

	VERTICAL,
	HORIZONTAL;

	private static final String[] NAMES = {"vertical", "horizontal"};

	public static final ValueFactory<UIOrientation> FACTORY = (s, defaultVal) -> {
		for (int i = 0; i < NAMES.length; i++) {
			if (NAMES[i].equalsIgnoreCase(s)) {
				return values()[i];
			}
		}
		return defaultVal;
	};
}
