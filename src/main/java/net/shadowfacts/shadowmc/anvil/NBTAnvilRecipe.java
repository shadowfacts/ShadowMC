package net.shadowfacts.shadowmc.anvil;

import lombok.AllArgsConstructor;
import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.util.ItemStackUtils;

/**
 * NBT matching anvil recipe
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class NBTAnvilRecipe extends BasicAnvilRecipe {

	@Override
	public boolean matches(ItemStack left, ItemStack right) {
		return ItemStackUtils.areItemStacksEqualWithNBT(getLeft(), left) &&
				ItemStackUtils.areItemStacksEqualWithNBT(getRight(), right);
	}
}

