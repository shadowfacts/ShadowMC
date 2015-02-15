package net.shadowfacts.shadowcore.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.config.Configuration;

import net.shadowfacts.shadowcore.ShadowCore;

/**
 * Class for handling all configuration stuff.
 * @author shadowfacts
 */
public class Configurator {

//	The all-mighty Configuration
	private static Configuration config;

	/**
	 * Properties
	 */
	public static boolean debuggerEnabled;
	public static boolean customSurfaceEnabled;
	public static String surfaceProviderType;
	public static boolean customNetherEnabled;
	public static String netherProviderType;
	public static boolean netherFortressEnabled;
	public static boolean customEndEnabled;
	public static String endProviderType;


	/**
	 *
	 * @param event The event that is provided by the preInit method in the @Mod class.
	 */
	public static void loadConfig(FMLPreInitializationEvent event) {
		ShadowCore.log.info("Loading configuration");

		config = new Configuration(event.getSuggestedConfigurationFile());

//		Load properties
		debuggerEnabled = config.getBoolean("debuggerEnabled", ConfigCategory.GENERAL, true, "Determines whether or not the debugger tool is enabled.");
		customSurfaceEnabled = config.getBoolean("customSurfaceEnabled", ConfigCategory.WorldProvider.SURFACE, true, "Is the custom surface generator enabled.");
		surfaceProviderType = config.getString("surfaceProviderType", ConfigCategory.WorldProvider.SURFACE, "void", "What type of custom world provider to use for the Overworld. Acceptable values are: void and custom");
		customNetherEnabled = config.getBoolean("customNetherEnabled", ConfigCategory.WorldProvider.NETHER, true, "Is the custom nether generator enabled.");
		netherProviderType = config.getString("netherProviderType", ConfigCategory.WorldProvider.NETHER, "void", "What type of custom world provider to use for the Nether. Acceptable values are void and custom.");
		netherFortressEnabled = config.getBoolean("netherFortressEnabled", ConfigCategory.WorldProvider.NETHER, true, "Should nether fortresses be generated in the nether, only applicable if custom nether generation is enabled and netherProviderType is void");
		customEndEnabled = config.getBoolean("customEndEnabled", ConfigCategory.WorldProvider.END, true, "Is the custom end generator enabled.");
		endProviderType = config.getString("endProviderType", ConfigCategory.WorldProvider.END, "void", "What type of custom world provider to use for the End. Acceptable values are void and custom");


		config.save();
	}
}
