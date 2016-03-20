package net.shadowfacts.shadowmc.event;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.asm.transformers.ItemStackTransformer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.anvil.AnvilManager;
import net.shadowfacts.shadowmc.anvil.AnvilRecipe;
import net.shadowfacts.shadowmc.config.ConfigManager;

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
		}
	}

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		ItemStack result = event.crafting;
		if (result.getItem() instanceof AchievementProvider) {
			((AchievementProvider)result.getItem()).grantAchievement(event.player, result);
		} else if (result.getItem() instanceof ItemBlock) {
			ItemBlock itemBlock = (ItemBlock)result.getItem();
			if (itemBlock.block instanceof AchievementProvider) {
				((AchievementProvider)itemBlock.block).grantAchievement(event.player, result);
			}
		}
	}

	@SubscribeEvent
	public void onItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
		ItemStack result = event.smelting;
		if (result.getItem() instanceof AchievementProvider) {
			((AchievementProvider)result.getItem()).grantAchievement(event.player, result);
		} else if (result.getItem() instanceof ItemBlock) {
			ItemBlock itemBlock = (ItemBlock)result.getItem();
			if (itemBlock.block instanceof AchievementProvider) {
				((AchievementProvider)itemBlock.block).grantAchievement(event.player, result);
			}
		}
	}

}
