package net.shadowfacts.forgelin.extensions

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import java.util.*

/**
 * @author shadowfacts
 */
operator fun IInventory.get(index: Int): ItemStack {
	return getStackInSlot(index)
}

operator fun IInventory.set(index: Int, stack: ItemStack) {
	setInventorySlotContents(index, stack)
}

operator fun IInventory.iterator(): Iterator<ItemStack> {
	return object: Iterator<ItemStack> {
		private var index = 0

		override fun next(): ItemStack {
			return this@iterator[index++]
		}

		override fun hasNext(): Boolean {
			return index < this@iterator.sizeInventory
		}
	}
}

inline fun IInventory.forEach(action: (ItemStack) -> Unit) {
	for (stack in this) action(stack)
}

inline fun IInventory.forEachIndexed(action: (Int, ItemStack) -> Unit) {
	var index = 0
	for (stack in this) action(index++, stack)
}

inline fun <C: MutableCollection<ItemStack>> IInventory.filterIndexedTo(destination: C, predicate: (Int, ItemStack) -> Boolean): C {
	forEachIndexed { i, stack ->
		if (predicate(i, stack)) destination.add(stack)
	}
	return destination
}

inline fun IInventory.filterIndexed(predicate: (Int, ItemStack) -> Boolean): List<ItemStack> {
	return filterIndexedTo(ArrayList(), predicate)
}

inline fun <C: MutableCollection<ItemStack>> IInventory.filterTo(destination: C, predicate: (ItemStack) -> Boolean): C {
	forEach {
		if (predicate(it)) destination.add(it)
	}
	return destination
}

inline fun IInventory.filter(predicate: (ItemStack) -> Boolean): List<ItemStack> {
	return filterTo(ArrayList(), predicate)
}

inline fun IInventory.first(predicate: (ItemStack) -> Boolean): ItemStack {
	forEach {
		if (predicate(it)) {
			return it
		}
	}
	throw NoSuchElementException("IInventory contains no element matching the predicate")
}

inline fun IInventory.firstOrEmpty(predicate: (ItemStack) -> Boolean): ItemStack {
	forEach {
		if (predicate(it)) {
			return it
		}
	}
	return ItemStack.EMPTY
}

inline fun IInventory.find(predicate: (ItemStack) -> Boolean): ItemStack {
	return firstOrEmpty(predicate)
}

inline fun IInventory.sumBy(selector: (ItemStack) -> Int): Int {
	var sum = 0
	forEach {
		sum += selector(it)
	}
	return sum
}

inline fun <R: Comparable<R>> IInventory.minBy(selector: (ItemStack) -> R): ItemStack {
	if (isEmpty) return ItemStack.EMPTY
	var minElem = this[0]
	var minValue = selector(minElem)
	for (i in 1..sizeInventory) {
		val e = this[i]
		val v = selector(e)
		if (minValue > v) {
			minElem = e
			minValue = v
		}
	}
	return minElem
}

inline fun <R: Comparable<R>> IInventory.maxBy(selector: (ItemStack) -> R): ItemStack {
	if (isEmpty) return ItemStack.EMPTY
	var maxElem = this[0]
	var maxValue = selector(maxElem)
	for (i in 1..sizeInventory) {
		val e = this[i]
		val v = selector(e)
		if (maxValue < v) {
			maxElem = e
			maxValue = v
		}
	}
	return maxElem
}