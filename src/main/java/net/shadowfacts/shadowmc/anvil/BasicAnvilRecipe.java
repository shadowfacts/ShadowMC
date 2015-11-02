package net.shadowfacts.shadowmc.anvil;

import lombok.AllArgsConstructor;
import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.util.ItemStackUtils;

/**
 * Basic anvil recipes, matches item and metadata only
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class BasicAnvilRecipe implements AnvilRecipe {

	private ItemStack left;
	private ItemStack right;
	private ItemStack result;
	private int xp;

	@Override
	public boolean matches(ItemStack left, ItemStack right) {
		return ItemStackUtils.areItemStacksEqual(getLeft(), left) &&
				ItemStackUtils.areItemStacksEqual(getRight(), right);
	}

	@Override
	public ItemStack getLeft() {
		return left;
	}

	@Override
	public ItemStack getRight() {
		return right;
	}

	@Override
	public ItemStack getResult() {
		return result;
	}

	@Override
	public int getXPCost() {
		return xp;
	}

}
