package net.shadowfacts.forgelin.extensions

import net.minecraft.entity.Entity
import net.minecraft.util.math.RayTraceResult

/**
 * @author shadowfacts
 */
fun Entity.rayTrace(distance: Double): RayTraceResult? {
	val eyePos = getPositionEyes(0f)
	val lookVec = getLook(0f)
	val vec = eyePos.addVector(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance)
	return world.rayTraceBlocks(eyePos, vec, false, false, true)
}