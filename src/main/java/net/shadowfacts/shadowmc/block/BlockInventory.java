package net.shadowfacts.shadowmc.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.tileentity.TileEntityItemHandler;

/**
 * @author shadowfacts
 */
public abstract class BlockInventory<TE extends TileEntityItemHandler> extends BlockTE<TE> {

	public BlockInventory(Material material, String name) {
		super(material, name);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			TE te = getTileEntity(world, pos);

			for (int slot = 0; slot < te.getSlots(); slot++) {
				ItemStack stack = te.getStackInSlot(slot);
				if (stack != null) {
					float randX = world.rand.nextFloat() * 0.8f + 0.1f;
					float randY = world.rand.nextFloat() * 0.8f + 0.1f;
					float randZ = world.rand.nextFloat() * 0.8f + 0.1f;

					while (stack.stackSize > 0) {
						int amount = Math.min(world.rand.nextInt(21) + 10, stack.stackSize);
						stack.stackSize -= amount;
						EntityItem item = new EntityItem(world, pos.getX() + randX, pos.getY() + randY, pos.getZ() + randZ);

						if (stack.hasTagCompound()) {
							item.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
						}

						item.motionX *= world.rand.nextGaussian() + 0.05f;
						item.motionY *= world.rand.nextGaussian() + 0.05f;
						item.motionZ *= world.rand.nextGaussian() + 0.05f;

						world.spawnEntityInWorld(item);
					}
				}
			}

		}

		super.breakBlock(world, pos, state);
	}

}
