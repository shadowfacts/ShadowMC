package net.shadowfacts.shadowmc.render;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Arrays;

/**
 * @author shadowfacts
 */
public class RenderBlockInfo {

	public double minX = 0f;
	public double minY = 0f;
	public double minZ = 0f;
	public double maxX = 1f;
	public double maxY = 1f;
	public double maxZ = 1f;

	public Block baseBlock = Blocks.sand;

	public IIcon texture = null;
	public IIcon[] textureArray = null;

	public boolean[] renderSide = new boolean[] { true, true, true, true, true, true };

	public int light = -1;
	public int brightness = -1;

	public RenderBlockInfo() {

	}

	public RenderBlockInfo(Block baseBlock, IIcon[] textureArray) {
		this.baseBlock = baseBlock;
		this.textureArray = textureArray;
	}

	public RenderBlockInfo(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		setBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	public void setSkyBlockLight(World world, int x, int y, int z, int light) {
		brightness = world.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, x, y, z) << 16 | light;
	}

	public float getBlockBrightness(IBlockAccess world, int x, int y, int z) {
		return baseBlock.getMixedBrightnessForBlock(world, x, y, z);
	}

	public void setBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		this.minX = minX;
		this.minY= minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	public void setRenderSingleSide(int side) {
		Arrays.fill(renderSide, false);
		renderSide[side] = true;
	}

	public void setRenderAllSides() {
		Arrays.fill(renderSide, true);
	}

	public void rotate() {
		double temp = minX;
		minX = minZ;
		minZ = temp;

		temp = maxX;
		maxX = maxZ;
		maxZ = temp;
	}

	public void reverseX() {
		double temp = minX;
		minX = 1 - maxX;
		maxX = 1 - temp;
	}

	public void reverseZ() {
		double temp = minZ;
		minZ = 1 - maxZ;
		maxZ = 1 - temp;
	}

	public IIcon getBlockTextureFromSide(int side) {
		if (texture != null) {
			return texture;
		}

		int index = side;
		if (textureArray == null || textureArray.length == 0) {
			return baseBlock.getBlockTextureFromSide(side);
		} else {
			if (index >= textureArray.length) {
				index = 0;
			}
			return textureArray[index];
		}
	}

}
