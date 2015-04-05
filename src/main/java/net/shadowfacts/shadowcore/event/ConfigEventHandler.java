package net.shadowfacts.shadowcore.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.shadowcore.ShadowCore;
import net.shadowfacts.shadowcore.config.ConfigManager;

/**
 * @author shadowfacts
 */
public class ConfigEventHandler {

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(ShadowCore.modId)) {
			ConfigManager.instance.load("Core");
		}
	}

}
