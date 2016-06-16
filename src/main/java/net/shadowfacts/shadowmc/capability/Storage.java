package net.shadowfacts.shadowmc.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author shadowfacts
 */
public class Storage<T extends INBTSerializable<NBTTagCompound>> implements Capability.IStorage<T> {

	@Override
	public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side) {
		return instance.serializeNBT();
	}

	@Override
	public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase tag) {
		instance.deserializeNBT((NBTTagCompound)tag);
	}

}
