package net.shadowfacts.shadowmc.oxygen.impl;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NBTTagCompound;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;

/**
 * Default oxygen handler implementation.
 *
 * @author shadowfacts
 */
@Getter
@Setter
public class OxygenHandlerImpl implements OxygenHandler {

	protected float capacity;
	protected float stored;
	protected float transferRate;

	/**
	 * Default no-args constructor, used by Forge.
	 */
	public OxygenHandlerImpl() {
		this(1000, 25);
	}

	/**
	 * @param capacity The maximum amount of oxygen that can be stored.
	 * @param transferRate The maximum amount of oxygen that can be transferred in 1 operation
	 */
	public OxygenHandlerImpl(float capacity, float transferRate) {
		this.capacity = capacity;
		this.transferRate = transferRate;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("Capacity", capacity);
		tag.setFloat("Stored", stored);
		tag.setFloat("TransferRate", transferRate);
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound tag) {
		capacity = tag.getFloat("Capacity");
		stored = tag.getFloat("Stored");
		transferRate = tag.getFloat("TransferRate");
	}

	public void load(OxygenHandler other) {
		capacity = other.getCapacity();
		stored = other.getStored();
		transferRate = other.getTransferRate();
	}

}
