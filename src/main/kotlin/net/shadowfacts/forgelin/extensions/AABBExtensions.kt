package net.shadowfacts.forgelin.extensions

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB

/**
 * @author shadowfacts
 */
fun AxisAlignedBB.rotateFace(side: EnumFacing): AxisAlignedBB {
	return when (side) {
		EnumFacing.DOWN -> this
		EnumFacing.UP -> AxisAlignedBB(minX, 1 - maxY, minZ, maxX, 1 - minY, maxZ)
		EnumFacing.NORTH -> AxisAlignedBB(minX, minZ, minY, maxX, maxZ, maxY)
		EnumFacing.SOUTH -> AxisAlignedBB(minX, minZ, 1 - maxY, maxX, maxZ, 1 - minY)
		EnumFacing.WEST -> AxisAlignedBB(minY, minZ, minX, maxY, maxZ, maxX)
		EnumFacing.EAST -> AxisAlignedBB(1 - maxY, minZ, minX, 1 - minY, maxZ, maxX)
	}
}