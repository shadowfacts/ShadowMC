package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 3 dimensional double coordinate
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord3d {

	public double x, y, z;


	public void add(Coord3d other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord3d copyAndAdd(Coord3d other) {
		return new Coord3d(x + other.x, y + other.y, z + other.z);
	}

	public void subtract(Coord3d other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord3d copyAndSubtract(Coord3d other) {
		return new Coord3d(x - other.x, y - other.y, z - other.z);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord3d copyAndOffset(ForgeDirection dir) {
		return new Coord3d(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
	}

	public Coord3d copy() {
		return new Coord3d(x, y, z);
	}

}
