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

	public static File configFile;

	public static void init(File configFile) {
		ShadowMCConfig.configFile = configFile;

		Configuration config = new Configuration(configFile);
		ConfigManager.load(ShadowMCConfig.class, Configuration.class, config);
	}

}
