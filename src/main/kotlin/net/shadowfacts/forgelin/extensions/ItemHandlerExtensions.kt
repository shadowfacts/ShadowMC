package net.shadowfacts.forgelin.extensions

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import java.util.*

/**
 * @author shadowfacts
 */
operator fun IItemHandler.get(slot: Int): ItemStack? {
	return getStackInSlot(slot)
}

operator fun IItemHandlerModifiable.set(slot: Int, stack: ItemStack?) {
	setStackInSlot(slot, stack)
}

operator fun IItemHandler.iterator(): Iterator<ItemStack?> {
	return object: Iterator<ItemStack?> {
		private var index = 0

		override fun next(): ItemStack? {
			return this@iterator[index++]
		}

		override fun hasNext(): Boolean {
			return index < this@iterator.slots
		}

	}
}

val IItemHandler.isEmpty: Boolean
	get() {
		forEach {
			if (it !== null) {
				return false
			}
		}
		return true
	}

inline fun IItemHandler.forEach(action: (ItemStack?) -> Unit) {
	for (stack in this) action(stack)
}

inline fun IItemHandler.forEachIndexed(action: (Int, ItemStack?) -> Unit) {
	var index = 0
	for (stack in this) action(index++, stack)
}

inline fun <C: MutableCollection<ItemStack?>> IItemHandler.filterIndexedTo(destination: C, predicate: (Int, ItemStack?) -> Boolean): C {
	forEachIndexed { i, stack ->
		if (predicate(i, stack)) destination.add(stack)
	}
	return destination
}

inline fun IItemHandler.filterIndexed(predicate: (Int, ItemStack?) -> Boolean): List<ItemStack?> {
	return filterIndexedTo(ArrayList(), predicate)
}

inline fun <C: MutableCollection<ItemStack?>> IItemHandler.filterTo(destination: C, predicate: (ItemStack?) -> Boolean): C {
	forEach {
		if (predicate(it)) destination.add(it)
	}
	return destination
}

inline fun IItemHandler.filter(predicate: (ItemStack?) -> Boolean): List<ItemStack?> {
	return filterTo(ArrayList(), predicate)
}

inline fun IItemHandler.first(predicate: (ItemStack?) -> Boolean): ItemStack? {
	forEach {
		if (predicate(it)) {
			return it
		}
	}
	throw NoSuchElementException("IItemHandler contains no element matching predicate")
}

inline fun IItemHandler.firstOrNull(predicate: (ItemStack?) -> Boolean): ItemStack? {
	forEach {
		if (predicate(it)) {
			return it
		}
	}
	return null
}

inline fun IItemHandler.find(predicate: (ItemStack?) -> Boolean): ItemStack? {
	return firstOrNull(predicate)
}

inline fun IItemHandler.sumBy(selector: (ItemStack?) -> Int): Int {
	var sum = 0
	forEach {
		sum += selector(it)
	}
	return sum
}

inline fun <R: Comparable<R>> IItemHandler.minBy(selector: (ItemStack?) -> R): ItemStack? {
	if (isEmpty) return null
	var minElem = this[0]
	var minValue = selector(minElem)
	for (i in 1..slots) {
		val e = this[i]
		val v = selector(e)
		if (minValue > v) {
			minElem = e
			minValue = v
		}
	}
	return minElem
}

inline fun <R: Comparable<R>> IItemHandler.maxBy(selector: (ItemStack?) -> R): ItemStack? {
	if (isEmpty) return null
	var maxElem = this[0]
	var maxValue = selector(maxElem)
	for (i in 1..slots) {
		val e = this[i]
		val v = selector(e)
		if (maxValue < v) {
			maxElem = e
			maxValue = v
		}
	}
	return maxElem
}