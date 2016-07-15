package test;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;

/**
 * @author shadowfacts
 */
public class BlockTest extends Block {

	public BlockTest() {
		super(Material.ROCK);
		setRegistryName("blockTest");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		player.openGui(ModTest.instance, 3, world, pos.getX(), pos.getY(), pos.getZ());
//		TileEntity te = world.getTileEntity(pos);
//		System.out.println(te.hasCapability(OxygenCaps.HANDLER, EnumFacing.NORTH));
//		System.out.println(te.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH));
		return true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
		return new TileEntityTest();
	}

}
