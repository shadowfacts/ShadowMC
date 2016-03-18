package net.shadowfacts.shadowmc.fluid;

import net.minecraft.entity.DataWatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author shadowfacts
 */
public class EntityFluidTank extends FluidTank {

//	Data watcher IDs
	public static int DEFAULT_AMOUNT = 20;
	public static int DEFAULT_NAME = 21;
	public static int DEFAULT_CAPACITY = 22;

	protected int amountID;
	protected int nameID;
	protected int capacityID;

	protected DataWatcher watcher;

	public EntityFluidTank(DataWatcher watcher, int amountID, int nameID, int capacityID, FluidStack stack, int capacity) {
		super(capacity);

		this.amountID = amountID;
		this.nameID = nameID;
		this.capacityID = capacityID;

		this.watcher = watcher;
		watcher.addObject(capacityID, 0);
		watcher.addObject(amountID, 0);
		watcher.addObject(nameID, "");

		setCapacity(capacity);
		setFluid(fluid);
	}

	public EntityFluidTank(DataWatcher watcher, int amountID, int nameID, int capacityID, int capacity) {
		this(watcher, amountID, nameID, capacityID, null, capacity);
	}

	public EntityFluidTank(DataWatcher watcher, FluidStack stack, int capacity) {	
		this(watcher, DEFAULT_AMOUNT, DEFAULT_NAME, DEFAULT_CAPACITY, stack, capacity);
	}

	public EntityFluidTank(DataWatcher watcher, int capacity) {
		this(watcher, DEFAULT_AMOUNT, DEFAULT_NAME, DEFAULT_CAPACITY, capacity);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		FluidStack fluid = getFluid();
		if (fluid != null) {
			fluid.writeToNBT(tag);
		} else {
			tag.setString("Empty", "");
		}

		return tag;
	}

	@Override
	public FluidTank readFromNBT(NBTTagCompound tag) {
		if (!tag.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag);
			setFluid(fluid);
		} else {
			setFluid(null);
		}
		return this;
	}

	private String getFluidName() {
		return watcher.getWatchableObjectString(nameID);
	}

	private Fluid getFluidFromDataWatcher() {
		String name = getFluidName();
		if (!name.isEmpty()) {
			return FluidRegistry.getFluid(name);
		} else {
			return null;
		}
	}

	private void setFluidName(String name) {
		watcher.updateObject(nameID, name);
	}

	private void setFluidAmount(int amount) {
		watcher.updateObject(amountID, amount);
	}

	@Override
	public void setCapacity(int capacity) {
		watcher.updateObject(capacityID, capacity);
	}

	@Override
	public void setFluid(FluidStack fluid) {
		if (fluid != null) {
			setFluidAmount(fluid.amount);
			setFluidName(FluidRegistry.getFluidName(fluid));
		} else {
			setFluidAmount(0);
			setFluidName("");
		}
	}

	@Override
	public FluidStack getFluid() {
		Fluid fluid = getFluidFromDataWatcher();
		if (fluid == null) return null;
		return new FluidStack(fluid, getFluidAmount());
	}

	@Override
	public int getFluidAmount() {
		return watcher.getWatchableObjectInt(amountID);
	}

}
