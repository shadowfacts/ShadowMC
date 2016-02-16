package net.shadowfacts.shadowmc.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

/**
 * @author shadowfacts
 */
public class EntityUtils {

	/**
	 * Because damn Entity#rayTrace is {@code SideOnly(Side.CLIENT)}
	 * @param entity
	 * @param distance
	 * @return
	 */
	public static MovingObjectPosition rayTrace(Entity entity, double distance) {
		Vec3 eyePos = entity.getPositionEyes(0);
		Vec3 lookVec = entity.getLook(0);
		Vec3 vec = eyePos.addVector(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance);
		return entity.worldObj.rayTraceBlocks(eyePos, vec, false, false, true);
	}

}
