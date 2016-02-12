package net.shadowfacts.shadowmc.nbt;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author shadowfacts
 */
public interface NBTDeserializer<T> {

	T deserialize(NBTTagCompound tag, String name);

}
