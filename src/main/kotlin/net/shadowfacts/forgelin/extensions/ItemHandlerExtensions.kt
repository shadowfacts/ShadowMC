package net.shadowfacts.forgelin.extensions

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

/**
 * @author shadowfacts
 */
operator fun IItemHandler.get(slot: Int): ItemStack? {
	return getStackInSlot(slot)
}

fun IItemHandler.forEach(action: (ItemStack?) -> Unit) {
	for (i in 0.until(slots)) {
		action(this[i])
	}
}

fun IItemHandler.forEachIndexed(action: (Int, ItemStack?) -> Unit) {
	for (i in 0.until(slots)) {
		action(i, this[i])
	}
}

fun IItemHandler.filter(predicate: (ItemStack?) -> Boolean): List<ItemStack?> {
	val list = mutableListOf<ItemStack?>()
	forEach {
		if (predicate(it)) {
			list.add(it)
		}
	}
	return list
}

fun IItemHandler.filterIndexed(predicate: (Int, ItemStack?) -> Boolean): List<ItemStack?> {
	val list = mutableListOf<ItemStack?>()
	forEachIndexed { i, it ->
		if (predicate(i, it)) {
			list.add(it)
		}
	}
	return list
}

fun IItemHandler.filterNotNull(): List<ItemStack> {
	val list = mutableListOf<ItemStack>()
	forEach {
		if (it != null) {
			list.add(it)
		}
	}
	return list
}

fun <R> IItemHandler.map(transform: (ItemStack?) -> R): List<R> {
	val list = mutableListOf<R>()
	forEach {
		list.add(transform(it))
	}
	return list
}

fun <R> IItemHandler.mapIndexed(transform: (Int, ItemStack?) -> R): List<R> {
	val list = mutableListOf<R>()
	forEachIndexed { i, it ->
		list.add(transform(i, it))
	}
	return list
}

fun <R> IItemHandler.mapNotNull(transform: (ItemStack) -> R): List<R> {
	val list = mutableListOf<R>()
	filterNotNull().forEach {
		list.add(transform(it))
	}
	return list
}

fun <R> IItemHandler.mapIndexedNotNull(transform: (Int, ItemStack) -> R): List<R> {
	val list = mutableListOf<R>()
	forEachIndexed { i, it ->
		if (it != null) {
			list.add(transform(i, it))
		}
	}
	return list
}