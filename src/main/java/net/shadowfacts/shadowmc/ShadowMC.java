package net.shadowfacts.shadowmc;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.config.ForgeConfigAdapter;
import net.shadowfacts.shadowmc.event.ShadowMCEventHandler;
import net.shadowfacts.shadowmc.proxy.CommonProxy;
import net.shadowfacts.shadowmc.structure.creator.BlockStructureCreator;
import net.shadowfacts.shadowmc.structure.creator.TESRStructureCreator;
import net.shadowfacts.shadowmc.structure.creator.TileEntityStructureCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.version, acceptedMinecraftVersions = "[1.9.4]", guiFactory = "net.shadowfacts.shadowmc.GUIFactory")
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String version = "@VERSION@";

	public static final Logger log = LogManager.getLogger(name);

	@SidedProxy(serverSide = "net.shadowfacts.shadowmc.proxy.CommonProxy", clientSide = "net.shadowfacts.shadowmc.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static ShadowMC instance;

	public static SimpleNetworkWrapper network;

//	Content
	public static BlockStructureCreator structureCreator;
	public static ItemBlock itemStructureCreator;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ForgeConfigAdapter.init();
		ShadowMCConfig.init(event.getModConfigurationDirectory());
		ShadowMCConfig.load();

		if (ShadowMCConfig.enableStructureCreator) {
			structureCreator = GameRegistry.register(new BlockStructureCreator());
			itemStructureCreator = (ItemBlock)GameRegistry.register(new ItemBlock(structureCreator).setRegistryName(structureCreator.getRegistryName()));
			GameRegistry.registerTileEntity(TileEntityStructureCreator.class, structureCreator.getRegistryName().toString());
			if (event.getSide() == Side.CLIENT) {
				preInitClient();
			}
		}

		proxy.preInit(event);

		MinecraftForge.EVENT_BUS.register(new ShadowMCEventHandler());
	}

	@SideOnly(Side.CLIENT)
	private void preInitClient() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStructureCreator.class, new TESRStructureCreator());
		ModelLoader.setCustomModelResourceLocation(itemStructureCreator, 0, new ModelResourceLocation("shadowmc:structureCreator", "inventory"));
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}

}
