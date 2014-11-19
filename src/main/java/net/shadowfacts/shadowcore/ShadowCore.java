package net.shadowfacts.shadowcore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.shadowfacts.shadowcore.command.CommandHandler;
import net.shadowfacts.shadowcore.debug.ItemDebugger;
import net.shadowfacts.shadowcore.proxy.CommonProxy;

@Mod(modid=ShadowCore.modId, name=ShadowCore.displayName, version=ShadowCore.version)
public class ShadowCore {

	public static final String modId = "shadowcore";
	public static final String displayName = "ShadowCore";
	public static final String version = "@VERSION@";
	public static final String serverProxy = "net.shadowfacts.shadowcore.proxy.CommonProxy";
	public static final String clientProxy = "net.shadowfacts.shadowcore.proxy.ClientProxy";
	
	@Instance(value=modId)
	public static ShadowCore instance;
	
	@SidedProxy(clientSide=clientProxy, serverSide=serverProxy)
	public static CommonProxy proxy;
	
	public static final Log log = new Log(modId);
	
	public static MinecraftServer server;

	// Debugger
	public static ItemDebugger debugger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		debugger = new ItemDebugger();
		debugger.setUnlocalizedName("shadowDebugger").setCreativeTab(CreativeTabs.tabMisc)
				.setTextureName(modId + ":shadowDebugger");

		GameRegistry.registerItem(debugger, "shadowDebugger");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		CommandHandler.initCommands(event);
		server = event.getServer();
	}
	
}
