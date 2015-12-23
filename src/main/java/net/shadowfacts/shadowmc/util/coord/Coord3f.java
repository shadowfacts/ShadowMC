package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 3 dimensional float coordinate
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord3f {

	public float x, y, z;


	public void add(Coord3f other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord3f copyAndAdd(Coord3f other) {
		return new Coord3f(x + other.x, y + other.y, z + other.z);
	}

	public void subtract(Coord3f other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord3f copyAndSubtract(Coord3f other) {
		return new Coord3f(x - other.x, y - other.y, z - other.z);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord3f copyAndOffset(ForgeDirection dir) {
		return new Coord3f(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
	}

	public Coord3f copy() {
		return new Coord3f(x, y, z);
	}

}
