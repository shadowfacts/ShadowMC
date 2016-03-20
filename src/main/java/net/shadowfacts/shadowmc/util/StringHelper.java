package net.shadowfacts.shadowmc.util;

import net.minecraft.util.text.translation.I18n;

/**
 * Some utilities for working with strings
 * @author shadowfacts
 */
public class StringHelper {

	public static String localize(String unlocalized) {
		return I18n.translateToLocal(unlocalized);
	}

	public static String localize(String unlocalized, Object... args) {
		return String.format(localize(unlocalized), args);
	}

}
