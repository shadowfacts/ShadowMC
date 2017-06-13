package net.shadowfacts.shadowmc.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class NoOpStorage<CAP> implements Capability.IStorage<CAP> {

	@Nullable
	@Override
	public NBTBase writeNBT(Capability<CAP> capability, CAP instance, EnumFacing side) {
		return null;
	}

	@Override
	public void readNBT(Capability<CAP> capability, CAP instance, EnumFacing side, NBTBase nbt) {
	}

}
