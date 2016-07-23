package net.shadowfacts.shadowmc.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public abstract class BlockTE<TE extends TileEntity> extends BlockBase implements TileEntityProvider<TE> {

	public BlockTE(Material material, String name) {
		super(material, name);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nonnull
	@Override
	public TE createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return createTileEntity();
	}

	@Override
	public abstract Class<TE> getTileEntityClass();

}
