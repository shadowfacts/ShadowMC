package net.shadowfacts.shadowcore.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Base block class, should be used for all mod block tile entities.
 * @author shadowfacts
 */
public class BaseModTileEntity extends TileEntity {

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		loadDataFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		addDataToNBT(nbt);
	}

	/**
	 * Override to load TileEntity specific data to the NBT tag.
	 */
	protected void loadDataFromNBT(NBTTagCompound nbt) {

	}

	/**
	 * Override to save TileEntity specific data to the NBT tag.
	 */
	protected void addDataToNBT(NBTTagCompound nbt) {

	}

}
