package net.shadowfacts.shadowmc.anvil;

import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.util.ItemStackUtils;

/**
 * NBT matching anvil recipe
 *
 * @author shadowfacts
 */
public class NBTAnvilRecipe extends BasicAnvilRecipe {

	public NBTAnvilRecipe(ItemStack left, ItemStack right, ItemStack result, int xp) {
		super(left, right, result, xp);
	}

	public NBTAnvilRecipe(ItemStack left, ItemStack right, ItemStack result) {
		super(left, right, result);
	}

	@Override
	public boolean matches(ItemStack left, ItemStack right) {
		return ItemStackUtils.areItemStacksEqualWithNBT(getLeft(), left) &&
				ItemStackUtils.areItemStacksEqualWithNBT(getRight(), right);
	}
}

