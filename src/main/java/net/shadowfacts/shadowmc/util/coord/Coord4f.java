package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 4 dimensional float coordinate
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord4f {

	public float x, y, z, dim;

	public void add(Coord4f other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord4f copyAndAdd(Coord4f other) {
		return new Coord4f(x + other.x, y + other.y, z + other.z, dim);
	}

	public void subtract(Coord4f other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord4f copyAndSubtract(Coord4f other) {
		return new Coord4f(x - other.x, y - other.y, z - other.z, dim);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord4f copyAndOffset(ForgeDirection dir) {
		return new Coord4f(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dim);
	}

	public Coord4f copy() {
		return new Coord4f(x, y, z, dim);
	}

}
