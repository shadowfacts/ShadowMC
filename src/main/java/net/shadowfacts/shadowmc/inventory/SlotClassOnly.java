package net.shadowfacts.shadowmc.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot that accepts only items with a certain {@link Class}
 *
 * @author shadowfacts
 */
public class SlotClassOnly extends Slot {

	private Class<?> itemClass;

	public SlotClassOnly(IInventory inventoryIn, int index, int xPosition, int yPosition, Class<?> itemClass) {
		super(inventoryIn, index, xPosition, yPosition);
		this.itemClass = itemClass;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return itemClass.isAssignableFrom(stack.getItem().getClass());
	}

}
