package net.shadowfacts.shadowmc;

import net.minecraft.launchwrapper.Launch;
import net.shadowfacts.shadowmc.config.Config;
import net.shadowfacts.shadowmc.config.ConfigProperty;

/**
 * @author shadowfacts
 */
@Config(name = "ShadowMC")
public class ShadowMCConfig {

	@ConfigProperty(comment = "Enable the debugger item")
	public static boolean enableDebugger = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");

}
