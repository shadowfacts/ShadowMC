package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 4 dimensional integer coordinate
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord4i {

	public int x, y, z, dim;

	
	public void add(Coord4i other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord4i copyAndAdd(Coord4i other) {
		return new Coord4i(x + other.x, y + other.y, z + other.z, dim);
	}

	public void subtract(Coord4i other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord4i copyAndSubtract(Coord4i other) {
		return new Coord4i(x - other.x, y - other.y, z - other.z, dim);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord4i copyAndOffset(ForgeDirection dir) {
		return new Coord4i(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dim);
	}

	public Coord4i copy() {
		return new Coord4i(x, y, z, dim);
	}

}
