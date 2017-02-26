package net.shadowfacts.shadowmc.util;

import net.minecraft.util.EnumFacing;

/**
 * Replacement for {@link EnumFacing} that is relative to a 'front' side
 *
 * @author shadowfacts
 */
public enum RelativeSide {

	FRONT,
	BACK,
	LEFT,
	RIGHT,
	TOP,
	BOTTOM;

	/**
	 * Get the absolute side equivalent to this relative side for a given 'front' side
	 * @param front The 'front' side
	 * @return The absolute side
	 */
	public EnumFacing forFront(EnumFacing front) {
		switch (this) {
			case FRONT:
				return front;
			case BACK:
				return front.getOpposite();
			case LEFT:
				return front.rotateY();
			case RIGHT:
				return front.rotateYCCW();
			case TOP:
				return EnumFacing.UP;
			case BOTTOM:
			default:
				return EnumFacing.DOWN;
		}
	}

	/**
	 * Get the relative side equivalent to the absolute side for a given 'front' side
	 * @param front The 'front' side
	 * @param side The absolute side
	 * @return The relative side
	 */
	public static RelativeSide forFacing(EnumFacing front, EnumFacing side) {
		if (side == EnumFacing.UP) return TOP;
		if (side == EnumFacing.DOWN) return BOTTOM;
		if (front == side) return FRONT;
		if (front == side.getOpposite()) return BACK;
		if (front == side.rotateYCCW()) return LEFT;
		if (front == side.rotateY()) return RIGHT;
		return TOP;
	}

}
