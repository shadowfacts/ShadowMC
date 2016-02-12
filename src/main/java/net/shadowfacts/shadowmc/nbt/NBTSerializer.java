package net.shadowfacts.shadowmc.nbt;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author shadowfacts
 */
@FunctionalInterface
public interface NBTSerializer<T> {

	void serialize(NBTTagCompound tag, String name, T val);

}
