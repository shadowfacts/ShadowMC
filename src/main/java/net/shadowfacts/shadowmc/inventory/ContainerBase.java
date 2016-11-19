package net.shadowfacts.shadowmc.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
public class ContainerBase extends Container {

	@Getter
	protected BlockPos pos;

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return player.getDistanceSq((double) pos.getX() + .5d, (double) pos.getY() + .5d, (double) pos.getZ() + .5d) <= 64;
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return null;
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return null;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
		boolean flag = false;
		int i = startIndex;

		if (reverseDirection) {
			i = endIndex - 1;
		}

		if (stack.isStackable()) {
			while (stack.getCount() > 0 && (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex)) {
				Slot slot = this.inventorySlots.get(i);
				ItemStack itemstack = slot.getStack();

				if (slot.isItemValid(stack)) {
					if (itemstack != null && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
						int j = itemstack.getCount() + stack.getCount();

						if (j <= stack.getMaxStackSize()) {
							stack.setCount(0);
							itemstack.setCount(j);
							slot.onSlotChanged();
							flag = true;
						} else if (itemstack.getCount() < stack.getMaxStackSize()) {
							stack.shrink(stack.getMaxStackSize() - itemstack.getCount());
							itemstack.setCount(stack.getMaxStackSize());
							slot.onSlotChanged();
							flag = true;
						}
					}
				}

				if (reverseDirection) {
					--i;
				} else {
					++i;
				}
			}
		}

		if (stack.getCount() > 0) {
			if (reverseDirection) {
				i = endIndex - 1;
			} else {
				i = startIndex;
			}

			while (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex) {
				Slot slot1 = (Slot)this.inventorySlots.get(i);
				ItemStack itemstack1 = slot1.getStack();

				// Forge: Make sure to respect isItemValid in the slot.
				if (itemstack1 == null && slot1.isItemValid(stack)) {
					slot1.putStack(stack.copy());
					slot1.onSlotChanged();
					stack.setCount(0);
					flag = true;
					break;
				}

				if (reverseDirection) {
					--i;
				} else {
					++i;
				}
			}
		}

		return flag;
	}

}
