package net.shadowfacts.shadowmc.util;


import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

/**
 * @author shadowfacts
 */
public class ClientUtils {

	public static String getKeyDisplayString(KeyBinding keyBinding) {
		return GameSettings.getKeyDisplayString(keyBinding.getKeyCode());
	}

}
