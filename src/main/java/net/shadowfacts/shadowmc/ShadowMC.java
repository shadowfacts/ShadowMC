package net.shadowfacts.shadowmc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.event.EventManager;
import net.shadowfacts.shadowmc.event.handler.ShadowMCEventHandler;
import net.shadowfacts.shadowmc.util.BytecodePrettyPrinter;
import org.objectweb.asm.Type;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.version)
public class ShadowMC {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String version = "@VERSION@";

	public static EventManager bus = new EventManager();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigManager.instance.configDir = event.getModConfigurationDirectory();
		ConfigManager.instance.register(name, ShadowMCConfig.class, modId);
		ConfigManager.instance.load(name);

//		Events
		bus.register(new ShadowMCEventHandler());

		Type intType = Type.getType(int.class);
		Type floatType = Type.getType(float.class);
		final String ON_ITEM_USE_DESC = Type.getMethodDescriptor(Type.getType(boolean.class),
				Type.getType(ItemStack.class), Type.getType(EntityPlayer.class), Type.getType(World.class),
				intType, intType, intType, intType, floatType, floatType, floatType);
		BytecodePrettyPrinter.printMethod("net.minecraft.item.ItemTool", "onItemUse", ON_ITEM_USE_DESC);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}



}
