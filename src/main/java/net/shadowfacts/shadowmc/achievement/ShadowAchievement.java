package net.shadowfacts.shadowmc.achievement;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

/**
 * @author shadowfacts
 */
public class ShadowAchievement extends Achievement {

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, ItemStack stack, Achievement parent) {
		super(statId, unlocalizedName, column, row, stack, parent);
		registerStat();
	}

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, Item item, Achievement parent) {
		super(statId, unlocalizedName, column, row, item, parent);
	}

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, Block block, Achievement parent) {
		super(statId, unlocalizedName, column, row, block, parent);
	}

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, ItemStack stack) {
		this(statId, unlocalizedName, column, row, stack, null);
		initIndependentStat();
	}

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, Item item) {
		this(statId, unlocalizedName, column, row, new ItemStack(item));
	}

	public ShadowAchievement(String statId, String unlocalizedName, int column, int row, Block block) {
		this(statId, unlocalizedName, column, row, new ItemStack(block));
	}

}
