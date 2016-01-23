package net.shadowfacts.shadowmc.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

/**
 * @author shadowfacts
 */
public interface AchievementProvider {

	Achievement getAchievement(ItemStack stack);

	default void grantAchievement(EntityPlayer player, ItemStack stack) {
		player.addStat(getAchievement(stack), 1);
	}

}
