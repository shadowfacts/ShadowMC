package net.shadowfacts.shadowmc.event.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.shadowmc.ShadowMC;

/**
 * @author shadowfacts
 */
public class ForgeEventHandler {

	public static ForgeEventHandler instance = new ForgeEventHandler();

	private ForgeEventHandler() {}

	@SubscribeEvent
	public void handleEvent(Event event) {
		ShadowMC.bus.post(event);
	}

}
