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
public abstract class BlockTE<TE extends TileEntity> extends BlockBase {

	public BlockTE(Material material, String name) {
		super(material, name);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nonnull
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);

	public abstract Class<TE> getTileEntityClass();

	@SuppressWarnings("unchecked")
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te != null) {
			if (getTileEntityClass().isAssignableFrom(te.getClass())) {
				return (TE)te;
			} else {
				throw new IllegalArgumentException(String.format("Invalid TileEntity type at %s, expected %s got %s", pos, getTileEntityClass().getName(), te.getClass().getName()));
			}
		} else {
			throw new IllegalArgumentException(String.format("No TileEntity at position %s", pos));
		}
	}

}
