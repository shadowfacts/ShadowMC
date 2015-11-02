package net.shadowfacts.shadowmc.anvil;

import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
public interface AnvilRecipe {

	boolean matches(ItemStack left, ItemStack right);

	ItemStack getLeft();

	ItemStack getRight();

	ItemStack getResult();

	int getXPCost();

}
