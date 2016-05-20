package net.shadowfacts.shadowmc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsolatedWorld implements IBlockAccess {

	private static final IsolatedWorld instance = new IsolatedWorld();

	private IBlockAccess world;
	private BlockPos pos;

	@Nullable
	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return this.pos.equals(pos) ? world.getTileEntity(pos) : null;
	}

	@Override
	public int getCombinedLight(BlockPos pos, int lightValue) {
		return world.getCombinedLight(pos, lightValue);
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		return this.pos.equals(pos) ? world.getBlockState(pos) : Blocks.AIR.getDefaultState();
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return !this.pos.equals(pos) || world.isAirBlock(pos);
	}

	@Override
	public Biome getBiomeGenForCoords(BlockPos pos) {
		return world.getBiomeGenForCoords(pos);
	}

	@Override
	public boolean extendedLevelsInChunkCache() {
		return world.extendedLevelsInChunkCache();
	}

	@Override
	public int getStrongPower(BlockPos pos, EnumFacing direction) {
		return this.pos.equals(pos) ? world.getStrongPower(pos, direction) : 0;
	}

	@Override
	public WorldType getWorldType() {
		return world.getWorldType();
	}

	@Override
	public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
		return this.pos.equals(pos) && world.isSideSolid(pos, side, _default);
	}

	public static IsolatedWorld get(IBlockAccess world, BlockPos pos) {
		instance.world = world;
		instance.pos = pos;
		return instance;
	}


}
