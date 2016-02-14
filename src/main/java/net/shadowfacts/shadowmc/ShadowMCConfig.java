package net.shadowfacts.shadowmc;

import net.shadowfacts.shadowmc.config.Config;
import net.shadowfacts.shadowmc.config.ConfigManager;

import java.io.File;

/**
 * @author shadowfacts
 */
@Config(name = "ShadowMC")
public class ShadowMCConfig {

	public static void init(File configDir) {
		ConfigManager.instance.configDir = configDir;
		ConfigManager.instance.register("ShadowMC", ShadowMCConfig.class, "ShadowMC");
		ConfigManager.instance.load("ShadowMC");
	}

}
