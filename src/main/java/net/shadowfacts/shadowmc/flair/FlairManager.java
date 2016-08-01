package net.shadowfacts.shadowmc.flair;

import net.minecraft.util.text.TextFormatting;

import java.util.*;

/**
 * @author shadowfacts
 */
public class FlairManager {

	private static List<Flair> flairs = new ArrayList<>();

	static {
		register(new NickFormatFlair(UUID.fromString("1854f5e6-0b21-4817-9284-6219f733790c"), TextFormatting.DARK_PURPLE)); // ShadowfactsDev
	}

	public static void register(Flair flair) {
		flairs.add(flair);
	}

	public static void initCommon() {
		flairs.forEach(Flair::initCommon);
	}

	public static void initClient() {
		flairs.forEach(Flair::initClient);
	}

}
