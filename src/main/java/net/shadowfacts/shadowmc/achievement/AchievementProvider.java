package net.shadowfacts.shadowmc.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
//TODO: fix once Forge handle's advancements
public interface AchievementProvider {

//	Achievement getAchievement(ItemStack stack);
//
	default void grantAchievement(EntityPlayer player, ItemStack stack) {
//		player.addStat(getAchievement(stack), 1);
	}

}
