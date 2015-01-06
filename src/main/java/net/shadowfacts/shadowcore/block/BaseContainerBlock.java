package net.shadowfacts.shadowcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Base class for all blocks that have containers/tile entities.
 * @author shadowfacts
 */
public abstract class BaseContainerBlock extends BaseTileEntityBlock {

	public BaseContainerBlock(String blockName) {
		this(blockName, Material.rock);
	}

	public BaseContainerBlock(String blockName, Material material) {
		super(blockName, material);
		this.isBlockContainer = true;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		super.breakBlock(world, x, y, z, block, par6);
		world.removeTileEntity(x, y, z);
	}

	public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6) {
		super.onBlockEventReceived(world, x, y, z, par5, par6);
		TileEntity te = world.getTileEntity(x, y, z);
		return te != null ? te.receiveClientEvent(par5, par6) : false;
	}

}
