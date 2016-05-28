package net.shadowfacts.shadowmc.structure.creator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.block.BlockTE;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class BlockStructureCreator extends BlockTE<TileEntityStructureCreator> {

	public BlockStructureCreator() {
		super(Material.ROCK, "structureCreator");
		setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		getTileEntity(world, pos).handleActivated(player, side);
		return true;
	}

	@Nonnull
	@Override
	public TileEntityStructureCreator createTileEntity(World world, IBlockState state) {
		return new TileEntityStructureCreator();
	}

	@Override
	public Class<TileEntityStructureCreator> getTileEntityClass() {
		return TileEntityStructureCreator.class;
	}

}
