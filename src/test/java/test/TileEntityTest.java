package test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;
import net.shadowfacts.shadowmc.oxygen.impl.OxygenTank;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityTest extends BaseTileEntity implements IInventory, ITickable {

	@CapHolder(capabilities = OxygenHandler.class)
	private OxygenTank oxygen = new OxygenTank(1000, 50, null);

	private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);

	public FluidTank tank = new FluidTank(new FluidStack(FluidRegistry.WATER, 250), 5000);

	public TileEntityTest() {
		oxygen.receive(33.3f, false);
	}

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

		chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(tag, chestContents);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		ItemStackHelper.saveAllItems(tag, chestContents);

		return tag;
	}

	@Override
	public int getSizeInventory() {
		return chestContents.size();
	}

	@Override
	public boolean isEmpty() {
		return !chestContents.stream()
				.filter(it -> !it.isEmpty())
				.findFirst()
				.isPresent();
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.chestContents.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (!this.chestContents.get(index).isEmpty())
		{
			if (this.chestContents.get(index).getCount() <= count)
			{
				ItemStack itemstack1 = this.chestContents.get(index);
				this.chestContents.set(index, ItemStack.EMPTY);
				this.markDirty();
				return itemstack1;
			}
			else
			{
				ItemStack itemstack = this.chestContents.get(index).splitStack(count);

				if (this.chestContents.get(index).getCount() == 0)
				{
					this.chestContents.set(index, ItemStack.EMPTY);
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
		if (!this.chestContents.get(index).isEmpty())
		{
			ItemStack itemstack = this.chestContents.get(index);
			this.chestContents.set(index, ItemStack.EMPTY);
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
		this.chestContents.set(index, stack);

		if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
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
