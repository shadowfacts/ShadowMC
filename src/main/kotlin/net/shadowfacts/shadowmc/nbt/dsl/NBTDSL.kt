package net.shadowfacts.shadowmc.nbt.dsl

import net.minecraft.nbt.*
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import java.util.*

/**
 * @author shadowfacts
 */
fun compound(tag: NBTTagCompound = NBTTagCompound(), init: CompoundContext.() -> Unit): NBTTagCompound {
	val ctx = CompoundContext(tag)
	ctx.init()
	return tag
}

fun list(tag: NBTTagList = NBTTagList(), init: ListContext.() -> Unit): NBTTagList {
	val ctx = ListContext(tag)
	ctx.init()
	return tag
}

private fun String.toTag() = NBTTagString(this)
private fun Byte.toTag() = NBTTagByte(this)
private fun Short.toTag() = NBTTagShort(this)
private fun Int.toTag() = NBTTagInt(this)
private fun Long.toTag() = NBTTagLong(this)
private fun Float.toTag() = NBTTagFloat(this)
private fun Double.toTag() = NBTTagDouble(this)
private fun ByteArray.toTag() = NBTTagByteArray(this)
private fun IntArray.toTag() = NBTTagIntArray(this)
private fun Boolean.toTag() = NBTTagByte(if (this) 1 else 0)

class CompoundContext(val tag: NBTTagCompound) {

	infix fun String.to(value: NBTBase) {
		tag.setTag(this, value)
	}

	infix fun String.to(value: String) {
		this to value.toTag()
	}

	infix fun String.to(value: Byte) {
		this to value.toTag()
	}

	infix fun String.to(value: Short) {
		this to value.toTag()
	}

	infix fun String.to(value: Int) {
		this to value.toTag()
	}

	infix fun String.to(value: Long) {
		this to value.toTag()
	}

	infix fun String.to(value: UUID) {
		tag.setUniqueId(this, value)
	}

	infix fun String.to(value: Float) {
		this to value.toTag()
	}

	infix fun String.to(value: Double) {
		this to value.toTag()
	}

	infix fun String.to(value: ByteArray) {
		this to value.toTag()
	}

	infix fun String.to(value: IntArray) {
		this to value.toTag()
	}

	infix fun String.to(value: Boolean) {
		this to value.toTag()
	}

	infix fun String.to(value: INBTSerializable<NBTBase>) {
		this to value.serializeNBT()
	}

	infix fun String.to(value: BlockPos) {
		this to value.toLong()
	}

	infix fun String.to(value: FluidStack) {
		this to value.writeToNBT(NBTTagCompound())
	}

}

class ListContext(val tag: NBTTagList) {

	operator fun set(index: Int, value: NBTBase) {
		tag.set(index, value)
	}

	operator fun set(index: Int, value: String) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Byte) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Short) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Int) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Long) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Float) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: ByteArray) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: IntArray) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: Boolean) {
		this[index] = value.toTag()
	}

	operator fun set(index: Int, value: INBTSerializable<NBTBase>) {
		this[index] = value.serializeNBT()
	}

	operator fun set(index: Int, value: BlockPos) {
		this[index] = value.toLong()
	}

	operator fun set(index: Int, value: FluidStack) {
		this[index] = value.writeToNBT(NBTTagCompound())
	}

	infix fun with(value: NBTBase) {
		tag.appendTag(value)
	}

	infix fun with(value: String) {
		this with value.toTag()
	}

	infix fun with(value: Byte) {
		this with value.toTag()
	}

	infix fun with(value: Short) {
		this with value.toTag()
	}

	infix fun with(value: Int) {
		this with value.toTag()
	}

	infix fun with(value: Long) {
		this with value.toTag()
	}

	infix fun with(value: ByteArray) {
		this with value.toTag()
	}

	infix fun with(value: IntArray) {
		this with value.toTag()
	}

	infix fun with(value: Boolean) {
		this with value.toTag()
	}

	infix fun with(value: INBTSerializable<NBTBase>) {
		this with value.serializeNBT()
	}

	infix fun with(value: BlockPos) {
		this with value.toLong()
	}

	infix fun with(value: FluidStack) {
		this with value.writeToNBT(NBTTagCompound())
	}

}
