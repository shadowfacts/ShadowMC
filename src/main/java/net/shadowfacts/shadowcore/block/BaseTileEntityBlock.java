package net.shadowfacts.shadowcore.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author shadowfacts
 */
public abstract class BaseTileEntityBlock extends BaseBlock implements ITileEntityProvider {

	public BaseTileEntityBlock(String modId, String blockName) {
		this(modId, blockName, Material.rock);
	}

	public BaseTileEntityBlock(String modId, String blockName, Material material) {
		super(modId, blockName, material);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int evtNum, int evtArg) {
		super.onBlockEventReceived(world, x, y, z, evtNum, evtArg);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			return te.receiveClientEvent(evtNum, evtArg);
		}
		return false;
	}

}
