package net.shadowfacts.shadowmc.util;

import cpw.mods.fml.common.event.*;

/**
 * @author shadowfacts
 */
public interface FMLLifecycleEventHandler {

	default void preInit(FMLPreInitializationEvent event) {}
	default void init(FMLInitializationEvent event) {}
	default void postInit(FMLPostInitializationEvent event) {}
	default void serverAboutToStart(FMLServerAboutToStartEvent event) {}
	default void serverStarting(FMLServerStartingEvent event) {}
	default void serverStarted(FMLServerStartedEvent event) {}
	default void serverStopping(FMLServerStoppingEvent event) {}
	default void serverStopped(FMLServerStoppedEvent event) {}


}
