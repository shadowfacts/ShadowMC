package net.shadowfacts.shadowmc.config;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigManager;
import net.shadowfacts.shadowmc.config.adapter.*;

/**
 * @author shadowfacts
 */
public class ForgeConfigAdapter {

	public static void init() {
		ConfigManager.registerTypeAdapter(Configuration.class, Boolean.class, BooleanAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, boolean.class, BooleanAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, boolean[].class, BooleanArrayAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, Double.class, DoubleAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, double.class, DoubleAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, double[].class, DoubleArrayAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, Integer.class, IntegerAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, int.class, IntegerAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, int[].class, IntegerArrayAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, Long.class, LongAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, long.class, LongAdapter.instance);
//		ConfigManager.registerTypeAdapter(Configuration.class, long[].class, LongArrayAdapter.instance); Forge config doesn't have this o_O
		ConfigManager.registerTypeAdapter(Configuration.class, String.class, StringAdapter.instance);
		ConfigManager.registerTypeAdapter(Configuration.class, String[].class, StringArrayAdapter.instance);
	}

}
