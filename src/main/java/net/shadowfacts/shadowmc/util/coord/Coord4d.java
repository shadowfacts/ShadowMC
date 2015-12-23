package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 4 dimensional double coordinate
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord4d {

	public double x, y, z, dim;

	
	public void add(Coord4d other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord4d copyAndAdd(Coord4d other) {
		return new Coord4d(x + other.x, y + other.y, z + other.z, dim);
	}

	public void subtract(Coord4d other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord4d copyAndSubtract(Coord4d other) {
		return new Coord4d(x - other.x, y - other.y, z - other.z, dim);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord4d copyAndOffset(ForgeDirection dir) {
		return new Coord4d(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dim);
	}

	public Coord4d copy() {
		return new Coord4d(x, y, z, dim);
	}

}
