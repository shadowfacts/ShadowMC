package test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTank;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityTest extends BaseTileEntity implements IInventory, ITickable {

	@CapHolder(capabilities = {"net.shadowfacts.shadowmc.oxygen.OxygenHandler"})
	private OxygenTank oxygen= new OxygenTank(1000, 50, null);

	private ItemStack[] chestContents = new ItemStack[27];

	public FluidTank tank = new FluidTank(new FluidStack(FluidRegistry.WATER, 250), 5000);

	@Override
	public void update() {
		if (tank.getFluidAmount() < tank.getCapacity()) {
			tank.setFluid(new FluidStack(FluidRegistry.WATER, tank.getFluidAmount() + 250));
		} else {
			tank.setFluid(new FluidStack(FluidRegistry.WATER, 250));
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.chestContents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length)
			{
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.chestContents.length; ++i)
		{
			if (this.chestContents[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte)i);
				this.chestContents[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		tag.setTag("Items", nbttaglist);
		return tag;
	}

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.chestContents[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.chestContents[index] != null)
		{
			if (this.chestContents[index].stackSize <= count)
			{
				ItemStack itemstack1 = this.chestContents[index];
				this.chestContents[index] = null;
				this.markDirty();
				return itemstack1;
			}
			else
			{
				ItemStack itemstack = this.chestContents[index].splitStack(count);

				if (this.chestContents[index].stackSize == 0)
				{
					this.chestContents[index] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (this.chestContents[index] != null)
		{
			ItemStack itemstack = this.chestContents[index];
			this.chestContents[index] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.chestContents[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}
}
