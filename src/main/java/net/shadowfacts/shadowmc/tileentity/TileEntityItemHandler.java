package net.shadowfacts.shadowmc.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class TileEntityItemHandler extends TileEntity implements IItemHandlerModifiable {

	protected ItemStack[] inventory;

	public TileEntityItemHandler(int size) {
		inventory = new ItemStack[size];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagList tagList = new NBTTagList();

		for (int slot = 0; slot < inventory.length; slot++) {
			ItemStack stack = inventory[slot];
			if (stack != null) {
				NBTTagCompound stackTag = stack.writeToNBT(new NBTTagCompound());
				stackTag.setInteger("_slotNum", slot);
				tagList.appendTag(stackTag);
			}
		}

		tag.setTag("Inventory", tagList);

		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList tagList = tag.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
			int slot = stackTag.getInteger("_slotNum");
			inventory[slot] = ItemStack.loadItemStackFromNBT(stackTag);
		}
	}

	@Override
	public int getSlots() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot < 0 || slot >= inventory.length) {
			throw new IllegalArgumentException("Invalid slot: " + slot);
		}
		return inventory[slot];
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (slot < 0 || slot >= inventory.length) {
			throw new IllegalArgumentException("Invalid slot: " + slot);
		}

		if (stack == null || stack.stackSize == 0) {
			return null;
		}

		ItemStack existing = inventory[slot];

		int limit = getStackLimit(slot, stack);

		if (existing != null) {
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) {
				return stack;
			}

			limit -= existing.stackSize;
		}

		if (limit <= 0) {
			return stack;
		}

		boolean reachedLimit = stack.stackSize > limit;

		if (!simulate) {
			if (existing == null) {
				inventory[slot] = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
			} else {
				existing.stackSize += reachedLimit ? limit : stack.stackSize;
			}
		}

		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.stackSize - limit) : null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot < 0 || slot >= inventory.length) {
			throw new IllegalArgumentException("Invalid slot: " + slot);
		}

		if (amount == 0) {
			return null;
		}

		ItemStack existing = inventory[slot];

		if (existing == null) {
			return null;
		}

		int toExtract = Math.min(amount, existing.getMaxStackSize());

		if (existing.stackSize <= toExtract) {
			if (!simulate) {
				inventory[slot] = null;
				onContentsChanged(slot);
			}
			return existing;
		} else {
			if (!simulate) {
				inventory[slot] = ItemHandlerHelper.copyStackWithSize(existing, existing.stackSize - toExtract);
				onContentsChanged(slot);
			}

			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		inventory[slot] = stack;
	}

	protected int getStackLimit(int slot, ItemStack stack) {
		return Math.min(64, stack.getMaxStackSize());
	}

	protected void onContentsChanged(int slot) {
		markDirty();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T)this;
		}
		return super.getCapability(capability, facing);
	}

}
