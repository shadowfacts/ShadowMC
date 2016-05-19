package net.shadowfacts.shadowmc.util;

import net.minecraft.client.resources.I18n;

/**
 * Some utilities for working with strings
 * @author shadowfacts
 */
public class StringHelper {

	/**
	 * Translates the unlocalized key to the localized version for the active language, formatting it with the given arguments.
	 * Should only be used on the dedicated client side.
	 * @param unlocalized
	 * @param args
	 * @return
	 */
	public static String localize(String unlocalized, Object... args) {
		return I18n.format(unlocalized, args);
	}

}
