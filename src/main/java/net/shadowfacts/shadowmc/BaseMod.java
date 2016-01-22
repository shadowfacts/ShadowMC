package net.shadowfacts.shadowmc;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.shadowlib.version.Version;
import net.shadowfacts.shadowmc.compat.CompatManager;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.proxy.BaseProxy;

import java.io.File;

/**
 * @author shadowfacts
 */
public abstract class BaseMod {

	@Getter
	protected CompatManager compatManager = new CompatManager(this);

	public abstract String getModId();

	public abstract String getName();

	public abstract String getVersionString();

	public abstract Class<?> getConfigClass();

	public abstract BaseProxy getProxy();

	public Version getVersion() {
		return new Version(getVersionString());
	}

	public void preInit(FMLPreInitializationEvent event) {
		if (getConfigClass() != null) initConfig(event.getModConfigurationDirectory());

		MinecraftForge.EVENT_BUS.register(this);

		getProxy().preInit(event);

		compatManager.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		getProxy().init(event);

		compatManager.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		getProxy().postInit(event);

		compatManager.postInit(event);
	}

	protected void initConfig(File configDir) {
		ConfigManager.instance.configDir = configDir;
		ConfigManager.instance.register(getModId(), getConfigClass(), getModId());
		ConfigManager.instance.load(getModId());
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(getModId())) {
			ConfigManager.instance.load(getModId());
		}
	}

}
