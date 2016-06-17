package net.shadowfacts.shadowmc.oxygen.impl;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class OxygenTankProvider implements ICapabilitySerializable<NBTTagCompound> {

	private OxygenTank tank;

	public OxygenTankProvider(int capacity, int transferRate) {
		tank = new OxygenTank(capacity, transferRate, null);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == OxygenCaps.HANDLER || capability == OxygenCaps.PROVIDER || capability == OxygenCaps.RECEIVER;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == OxygenCaps.HANDLER || capability == OxygenCaps.PROVIDER || capability == OxygenCaps.RECEIVER) {
			return (T)tank;
		} else {
			return null;
		}
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return tank.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound tag) {
		tank.deserializeNBT(tag);
	}

}
