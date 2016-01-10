package net.shadowfacts.shadowmc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.event.handler.ShadowMCEventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.version)
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String version = "@VERSION@";

	public static final Logger log = LogManager.getLogger(name);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigManager.instance.configDir = event.getModConfigurationDirectory();
		ConfigManager.instance.register(name, ShadowMCConfig.class, modId);
		ConfigManager.instance.load(name);

//		Events
		MinecraftForge.EVENT_BUS.register(new ShadowMCEventHandler());
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}

}
