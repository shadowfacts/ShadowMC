package net.shadowfacts.shadowmc.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author shadowfacts
 */
public interface AutoNBTSerializable extends INBTSerializable<NBTTagCompound> {

	@Override
	default NBTTagCompound serializeNBT() {
		return AutoNBTSerializer.serialize(getClass(), this);
	}

	@Override
	default void deserializeNBT(NBTTagCompound tag) {
		AutoNBTSerializer.deserialize(getClass(), this, tag);
	}

}
