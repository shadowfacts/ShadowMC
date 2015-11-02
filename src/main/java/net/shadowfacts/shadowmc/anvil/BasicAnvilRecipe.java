package net.shadowfacts.shadowmc.anvil;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.util.ItemStackUtils;

/**
 * Basic anvil recipes, matches item and metadata only
 *
 * @author shadowfacts
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class BasicAnvilRecipe implements AnvilRecipe {

	@NonNull private final ItemStack left;
	@NonNull private final ItemStack right;
	@NonNull private final ItemStack result;
	private int xp = 1;

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
