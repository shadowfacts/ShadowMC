package net.shadowfacts.shadowcore.config;

/**
 * Main config file for ShadowCore.
 * @author shadowfacts
 */
@Config(name = "ShadowCore")
public class Configuration {

	@ConfigProperty(category = "misc", comment = "Enable the debugger item.")
	public static boolean debugItemEnabled = true;

//	WorldGen
	@ConfigProperty(category = ConfigCategory.WorldProvider.SURFACE, comment = "Enable custom surface generation.")
	public static boolean customSurfaceEnabled = true;

	@ConfigProperty(category = ConfigCategory.WorldProvider.SURFACE, comment = "What type of custom world provider to use for the Overworld.", stringValidValues = {"void"})
	public static String surfaceProviderType = "void";

	@ConfigProperty(category = ConfigCategory.WorldProvider.NETHER, comment = "Enable custom Nether generation.")
	public static boolean customNetherEnabled = true;

	@ConfigProperty(category = ConfigCategory.WorldProvider.NETHER, comment = "What type of custom world provider to use for the Nether.", stringValidValues = {"void"})
	public static String netherProviderType = "void";

	@ConfigProperty(category = ConfigCategory.WorldProvider.NETHER, comment = "Should nether fortresses be generated in the nether, only applicable if custom nether generation is enabled and netherProviderType is void.")
	public static boolean netherFortressEnabled = true;

	@ConfigProperty(category = ConfigCategory.WorldProvider.END, comment = "Enable custom End generation.")
	public static boolean customEndEnabled = true;

	@ConfigProperty(category = ConfigCategory.WorldProvider.END, comment = "What type of custom world provider to use for the End.", stringValidValues = {"void"})
	public static String endProviderType = "void";

}
