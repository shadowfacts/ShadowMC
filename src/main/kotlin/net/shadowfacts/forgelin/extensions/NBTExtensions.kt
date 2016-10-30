package net.shadowfacts.forgelin.extensions

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import java.util.*

/**
 * @author shadowfacts
 */
fun NBTTagList.forEach(action: (NBTBase) -> Unit) {
	for (i in 0.until(tagCount())) {
		action(get(i))
	}
}

operator fun NBTTagList.get(index: Int): NBTBase {
	return get(index)
}

operator fun NBTTagCompound.get(key: String): NBTBase {
	return getTag(key)
}

operator fun NBTTagCompound.set(key: String, value: NBTBase) {
	setTag(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Byte) {
	setByte(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Short) {
	setShort(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Int) {
	setInteger(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Long) {
	setLong(key, value)
}

operator fun NBTTagCompound.set(key: String, value: UUID) {
	setUniqueId(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Float) {
	setFloat(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Double) {
	setDouble(key, value)
}

operator fun NBTTagCompound.set(key: String, value: ByteArray) {
	setByteArray(key, value)
}

operator fun NBTTagCompound.set(key: String, value: IntArray) {
	setIntArray(key, value)
}

operator fun NBTTagCompound.set(key: String, value: Boolean){
	setBoolean(key, value)
}