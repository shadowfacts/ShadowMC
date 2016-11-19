package net.shadowfacts.shadowmc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.shadowfacts.shadowmc.capability.Storage;
import net.shadowfacts.shadowmc.command.CommandShadow;
import net.shadowfacts.shadowmc.config.ForgeConfigAdapter;
import net.shadowfacts.shadowmc.event.ShadowMCEventHandler;
import net.shadowfacts.shadowmc.flair.FlairManager;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.OxygenProvider;
import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenHandlerImpl;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenProviderImpl;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenReceiverImpl;
import net.shadowfacts.shadowmc.proxy.CommonProxy;
import net.shadowfacts.shadowmc.structure.creator.TESRStructureCreator;
import net.shadowfacts.shadowmc.structure.creator.TileEntityStructureCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.version, guiFactory = "net.shadowfacts.shadowmc.GUIFactory")
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String version = "3.7.0";

	public static final Logger log = LogManager.getLogger(name);

	@SidedProxy(serverSide = "net.shadowfacts.shadowmc.proxy.CommonProxy", clientSide = "net.shadowfacts.shadowmc.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static ShadowMC instance;

	public static SimpleNetworkWrapper network;

//	Content
	public static ShadowItems items = new ShadowItems();
	public static ShadowBlocks blocks = new ShadowBlocks();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ForgeConfigAdapter.init();
		ShadowMCConfig.init(event.getModConfigurationDirectory());
		ShadowMCConfig.load();

		items.init();
		blocks.init();

		ShadowRecipes.init();

		if (event.getSide() == Side.CLIENT) {
			preInitClient();
		}

		proxy.preInit(event);

		registerCapabilities();

		MinecraftForge.EVENT_BUS.register(new ShadowMCEventHandler());

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		FlairManager.initCommon();
		proxy.init(event);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandShadow.INSTANCE);
	}

	@SideOnly(Side.CLIENT)
	private void preInitClient() {
		if (ShadowMCConfig.enableStructureCreator) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStructureCreator.class, new TESRStructureCreator());
		}
	}

	private void registerCapabilities() {
		CapabilityManager.INSTANCE.register(OxygenHandler.class, new Storage<>(), OxygenHandlerImpl.class);
		CapabilityManager.INSTANCE.register(OxygenReceiver.class, new Storage<>(), OxygenReceiverImpl.class);
		CapabilityManager.INSTANCE.register(OxygenProvider.class, new Storage<>(), OxygenProviderImpl.class);
	}

}
