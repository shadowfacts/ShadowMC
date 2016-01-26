package net.shadowfacts.shadowmc.util;

import org.lwjgl.input.Keyboard;

/**
 * @author shadowfacts
 */
public class KeyboardHelper {

	public static boolean isShiftPressed() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

}
