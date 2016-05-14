package net.shadowfacts.shadowmc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.config.ForgeConfigAdapter;
import net.shadowfacts.shadowmc.event.ShadowMCEventHandler;
import net.shadowfacts.shadowmc.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.versionString)
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String versionString = "3.1.0";

	public static final Logger log = LogManager.getLogger(name);

	@SidedProxy(serverSide = "net.shadowfacts.shadowmc.proxy.CommonProxy", clientSide = "net.shadowfacts.shadowmc.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static ShadowMC instance;

	public static SimpleNetworkWrapper network;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ForgeConfigAdapter.init();
		ShadowMCConfig.init(new File(event.getModConfigurationDirectory(), name + ".cfg"));

		proxy.preInit(event);

		MinecraftForge.EVENT_BUS.register(new ShadowMCEventHandler());
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}

}
