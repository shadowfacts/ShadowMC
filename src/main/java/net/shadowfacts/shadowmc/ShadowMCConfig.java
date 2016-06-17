package net.shadowfacts.shadowmc;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.Config;
import net.shadowfacts.config.ConfigManager;

import java.io.File;

/**
 * @author shadowfacts
 */
@Config(name = "ShadowMC")
public class ShadowMCConfig {

	public static Configuration config;

	@Config.Prop(category = "dev", description = "Enable the structure creator block (only for mod/pack devs)")
	public static boolean enableStructureCreator = false;

	@Config.Prop(description = "Enable the iron nugget (will only be created if no other iron nugget is present)")
	public static boolean enableNuggetIron = true;

	public static void init(File configDir) {
		config = new Configuration(new File(configDir, "shadowfacts/ShadowMC.cfg"));
	}

	public static void load() {
		ConfigManager.load(ShadowMCConfig.class, Configuration.class, config);
		if (config.hasChanged()) config.save();
	}

}
