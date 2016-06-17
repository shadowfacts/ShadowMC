package net.shadowfacts.shadowmc.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;

/**
 * @author shadowfacts
 */
public class SlotOxygenHandler extends SlotItemHandler {

	public SlotOxygenHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.hasCapability(OxygenCaps.HANDLER, null);
	}

}
