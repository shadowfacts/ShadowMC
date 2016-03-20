package net.shadowfacts.shadowmc.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

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
	public static RayTraceResult rayTrace(Entity entity, double distance) {
		Vec3d eyePos = entity.getPositionEyes(0);
		Vec3d lookVec = entity.getLook(0);
		Vec3d vec = eyePos.addVector(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance);
		return entity.worldObj.rayTraceBlocks(eyePos, vec, false, false, true);
	}

}
