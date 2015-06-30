package net.shadowfacts.shadowmc.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Simple wrapper class for XYZ coordinates.
 * @author shadowfacts
 */
public class CoordTriplet implements Comparable {

	public int x, y, z;

	public CoordTriplet(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public CoordTriplet(Entity e) {
		this(MathHelper.floor_double(e.posX), MathHelper.floor_double(e.posY), MathHelper.floor_double(e.posZ));
	}

	public CoordTriplet(TileEntity te) {
		this(te.xCoord, te.yCoord, te.zCoord);
	}

	public Block getBlock(World world) {
		return world.getBlock(this.x, this.y, this.z);
	}

	public TileEntity getTileEntity(World world) {
		return world.getTileEntity(this.x, this.y, this.z);
	}

	public int getChunkX() {
		return x >> 4;
	}

	public int getChunkZ() {
		return z >> 4;
	}

	public long getChunkXZHash() {
		return ChunkCoordIntPair.chunkXZ2Int(x >> 4, z >> 4);
	}

	public void translate(ForgeDirection dir) {
		this.x += dir.offsetX;
		this.y += dir.offsetY;
		this.z += dir.offsetZ;
	}

	public CoordTriplet copy() {
		return new CoordTriplet(x, y, z);
	}

	public CoordTriplet[] getNeighbors() {
		return new CoordTriplet[] {
				new CoordTriplet(x + 1, y, z),
				new CoordTriplet(x - 1, y, z),
				new CoordTriplet(x, y + 1, z),
				new CoordTriplet(x, y - 1, z),
				new CoordTriplet(x, y, z + 1),
				new CoordTriplet(x, y, z - 1)
		};
	}

	public ForgeDirection getDirectionFromSourceCoords(int x, int y, int z) {
		if(this.x < x) { return ForgeDirection.WEST; }
		else if(this.x > x) { return ForgeDirection.EAST; }
		else if(this.y < y) { return ForgeDirection.DOWN; }
		else if(this.y > y) { return ForgeDirection.UP; }
		else if(this.z < z) { return ForgeDirection.SOUTH; }
		else if(this.z > z) { return ForgeDirection.NORTH; }
		else { return ForgeDirection.UNKNOWN; }
	}

	public ForgeDirection getOppositeDirectionFromSourceCoords(int x, int y, int z) {
		if(this.x < x) { return ForgeDirection.EAST; }
		else if(this.x > x) { return ForgeDirection.WEST; }
		else if(this.y < y) { return ForgeDirection.UP; }
		else if(this.y > y) { return ForgeDirection.DOWN; }
		else if(this.z < z) { return ForgeDirection.NORTH; }
		else if(this.z > z) { return ForgeDirection.SOUTH; }
		else { return ForgeDirection.UNKNOWN; }
	}

	// Stuff for comparing
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else if (other instanceof CoordTriplet) {
			CoordTriplet otherTriplet = (CoordTriplet)other;
			return this.x == otherTriplet.x && this.y == otherTriplet.y && this.z == otherTriplet.z;
		} else {
			return false;
		}
	}

	public boolean equals(int x, int y, int z) {
		return this.x == x && this.y == y && this.z == z;
	}

	// IComparable
	@Override
	public int compareTo(Object o) {
		if (o instanceof CoordTriplet) {
			CoordTriplet other = (CoordTriplet)o;
			if (this.x < other.x) { return -1; }
			else if (this.x > other.x) { return 1; }
			else if (this.y < other.y) { return -1; }
			else if (this.y > other.y) { return 1; }
			else if (this.z < other.z) { return -1; }
			else if (this.z > other.z) { return 1; }
			else { return 0; }
		}
		return 0;
	}
}
