package net.shadowfacts.shadowmc.util;

/**
 * @author shadowfacts
 */
public enum MouseButton {

	LEFT,
	RIGHT,
	MIDDLE,
	UNKNOWN;

	public static MouseButton get(int id) {
		if (id < values().length - 1) {
			return values()[id];
		}
		return UNKNOWN;
	}

}
