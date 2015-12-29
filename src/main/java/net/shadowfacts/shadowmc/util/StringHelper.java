package net.shadowfacts.shadowmc.util;

import net.minecraft.util.StatCollector;

/**
 * Some utilities for working with strings
 * @author shadowfacts
 */
public class StringHelper {

	public static String localize(String unlocalized) {
		return StatCollector.translateToLocal(unlocalized);
	}

	public static String localize(String unlocalized, Object... args) {
		return String.format(localize(unlocalized), args);
	}

}
