package net.shadowfacts.forgelin.extensions

import net.minecraft.item.ItemStack

/**
 * @author shadowfacts
 */
fun List<ItemStack>.containsStack(stack: ItemStack, checkNBT: Boolean = false): Boolean {
	return any {
		it.item == stack.item && it.itemDamage == stack.itemDamage && (if (checkNBT) it.tagCompound?.equals(stack.tagCompound) ?: false else true)
	}
}