package net.shadowfacts.shadowmc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.shadowfacts.shadowmc.command.CommandHandler;
import net.shadowfacts.shadowmc.event.handler.ShadowMCEventHandler;
import net.shadowfacts.shadowmc.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = ShadowMC.modId, name = ShadowMC.name, version = ShadowMC.versionString)
public class ShadowMC extends BaseMod {

	public static final String modId = "shadowmc";
	public static final String name = "ShadowMC";
	public static final String versionString = "3.0.0-SNAPSHOT";

	public static final Logger log = LogManager.getLogger(name);

	@SidedProxy(serverSide = "net.shadowfacts.shadowmc.proxy.CommonProxy", clientSide = "net.shadowfacts.shadowmc.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static ShadowMC instance;

	@Override
	public String getModId() {
		return modId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getVersionString() {
		return versionString;
	}

	@Override
	public CommonProxy getProxy() {
		return proxy;
	}

	@Override
	public Class<?> getConfigClass() {
		return ShadowMCConfig.class;
	}

	@Mod.EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(new ShadowMCEventHandler());
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandHandler.instance);
	}

}
