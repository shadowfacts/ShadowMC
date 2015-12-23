package net.shadowfacts.shadowmc.event.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.shadowfacts.shadowmc.anvil.AnvilManager;
import net.shadowfacts.shadowmc.anvil.AnvilRecipe;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.event.ToolUseEvent;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public class ShadowMCEventHandler {

	@SubscribeEvent
	public void anvilUpdate(AnvilUpdateEvent event) {
		if (event.left == null || event.right == null) return;

		Optional<AnvilRecipe> optionalRecipe = AnvilManager.getInstance().getRecipe(event.left, event.right);

		if (optionalRecipe.isPresent()) {
			AnvilRecipe recipe = optionalRecipe.get();

			event.cost = Math.max(recipe.getXPCost(), 1);
			event.output = recipe.getResult();
			event.materialCost = 1;
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (ConfigManager.instance.getModIdConfigNameMap().containsKey(event.modID)) {
			String name = ConfigManager.instance.getModIdConfigNameMap().get(event.modID);
			ConfigManager.instance.load(name);

			Configuration cfg = ConfigManager.instance.getConfigurationObject(name);
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	@SubscribeEvent
	public void test(ToolUseEvent event) {
		System.out.println("test");
	}

}
