package net.shadowfacts.shadowmc.oxygen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Anything that stores oxygen
 *
 * You should probably not implement this yourself.
 *
 * @author shadowfacts
 */
public interface OxygenHandler extends INBTSerializable<NBTTagCompound> {

	/**
	 * @return The maximum amount of oxygen that this handler can store
	 */
	float getCapacity();

	/**
	 * @return The current amount of oxygen stored
	 */
	float getStored();

	/**
	 * @return The maximum amount of oxygen that can be transfered in 1 operation
	 */
	float getTransferRate();

}

