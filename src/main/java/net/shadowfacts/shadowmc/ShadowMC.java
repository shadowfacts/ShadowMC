package net.shadowfacts.shadowmc;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.debug.ItemDebugger;
import net.shadowfacts.shadowmc.event.handler.ForgeEventHandler;
import net.shadowfacts.shadowmc.event.handler.ShadowMCEventHandler;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.version)
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String version = "@VERSION@";

	public static ItemDebugger debugger;

	public static EventBus bus = new EventBus();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigManager.instance.configDir = event.getModConfigurationDirectory();
		ConfigManager.instance.register(name, ShadowMCConfig.class, modId);
		ConfigManager.instance.load(name);

		debugger = new ItemDebugger();
		if (ShadowMCConfig.enableDebugger) GameRegistry.registerItem(debugger, "debugger");

//		Forge/FML events
		FMLCommonHandler.instance().bus().register(ForgeEventHandler.instance);
		MinecraftForge.EVENT_BUS.register(ForgeEventHandler.instance);

//		Events
		bus.register(new ShadowMCEventHandler());
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}

}
