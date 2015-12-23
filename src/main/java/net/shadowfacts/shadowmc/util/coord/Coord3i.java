package net.shadowfacts.shadowmc.util.coord;

import lombok.AllArgsConstructor;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 3 dimensional integer coordinate.
 *
 * @author shadowfacts
 */
@AllArgsConstructor
public class Coord3i {

	public int x, y, z;


	public int getChunkX() {
		return x >> 4;
	}

	public int getChunkZ() {
		return z >> 4;
	}

	public void add(Coord3i other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}

	public Coord3i copyAndAdd(Coord3i other) {
		return new Coord3i(x + other.x, y + other.y, z + other.z);
	}

	public void subtract(Coord3i other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
	}

	public Coord3i copyAndSubtract(Coord3i other) {
		return new Coord3i(x - other.x, y - other.y, z - other.z);
	}

	public void offset(ForgeDirection dir) {
		x += dir.offsetX;
		y += dir.offsetY;
		z += dir.offsetZ;
	}

	public Coord3i copyAndOffset(ForgeDirection dir) {
		return new Coord3i(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
	}

	public Coord3i copy() {
		return new Coord3i(x, y, z);
	}

	public Coord3i[] getNeighbors() {
		return new Coord3i[] {
				new Coord3i(x + 1, y, z),
				new Coord3i(x - 1, y, z),
				new Coord3i(x, y + 1, z),
				new Coord3i(x, y - 1, z),
				new Coord3i(x, y, z + 1),
				new Coord3i(x, y, z - 1)
		};
	}

	public ForgeDirection getDirectionFromSourceCoords(int x, int y, int z) {
		if (this.x < x) return ForgeDirection.WEST;
		else if (this.x > x) return ForgeDirection.EAST;
		else if (this.y < y) return ForgeDirection.DOWN;
		else if (this.y > y) return ForgeDirection.UP;
		else if (this.z < z) return ForgeDirection.SOUTH;
		else if (this.z > z) return ForgeDirection.NORTH;
		else return ForgeDirection.UNKNOWN;
	}

	public ForgeDirection getDirectionFromSourceCoords(Coord3i coord) {
		return getDirectionFromSourceCoords(coord.x, coord.y, coord.z);
	}

	public ForgeDirection getOppositeDirectionFromSourceCoords(int x, int y, int z) {
		return getDirectionFromSourceCoords(x, y, z).getOpposite();
	}

	public ForgeDirection getOppositeDirectionFromSourceCoords(Coord3i coord) {
		return getOppositeDirectionFromSourceCoords(coord.x, coord.y, coord.z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;

		if (obj != null && obj instanceof Coord3i) {
			Coord3i other = (Coord3i)obj;
			return equals(other.x, other.y, other.z);
		}

		return super.equals(obj);
	}

	public boolean equals(int x, int y, int z) {
		return this.x == x &&
				this.y == y &&
				this.z == z;
	}
}
