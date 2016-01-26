package net.shadowfacts.shadowmc.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
public class InventoryUtils {

	public static boolean canAddToInventory(ItemStack stack, IInventory inventory) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return true;
			}
		}
		return false;
	}


	public static void addToInventory(ItemStack stack, IInventory inventory) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				inventory.setInventorySlotContents(i, stack);
				return;
			}
		}
	}
}
