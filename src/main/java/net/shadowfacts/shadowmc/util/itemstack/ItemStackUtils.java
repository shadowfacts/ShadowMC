package net.shadowfacts.shadowmc.util.itemstack;

import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
public class ItemStackUtils {

	public static boolean areItemStacksEqual(ItemStack stack, ItemStack other) {
		if (stack == null || other == null) {
			return stack == null && other == null;
		} else if (stack.getItem() == null || other.getItem() == null) {
			return stack.getItem() == null && other.getItem() == null;
		}
		return stack.isItemEqual(other) &&
				stack.getItemDamage() == other.getItemDamage();
	}

	public static boolean areItemStacksEqualWithNBT(ItemStack stack, ItemStack other) {
		if (stack == null || other == null) {
			return stack == null && other == null;
		} else if (stack.getItem() == null || other.getItem() == null) {
			return stack.getItem() == null && other.getItem() == null;
		} else if (stack.isItemEqual(other) && stack.getItemDamage() == other.getItemDamage()) {
			if (stack.stackTagCompound == null) {
				return other.stackTagCompound == null;
			}

			return stack.stackTagCompound.equals(other.stackTagCompound);
		}

		return false;
	}

}
