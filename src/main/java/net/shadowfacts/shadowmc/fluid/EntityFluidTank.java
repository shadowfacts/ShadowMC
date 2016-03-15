package net.shadowfacts.shadowmc.fluid;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author shadowfacts
 */
public class EntityFluidTank extends FluidTank {

//	Data watcher IDs
	@Getter @Setter
	public int AMOUNT = 30;
	@Getter @Setter
	public int NAME = 31;
	@Getter @Setter
	public int CAPACITY = 32;

	protected DataWatcher watcher;

	public EntityFluidTank(FluidStack stack, int capacity) {
		super(capacity);

		setCapacity(capacity);
		setFluid(fluid);
	}

	public EntityFluidTank(int capacity) {
		this(null, capacity);
	}

	public EntityFluidTank(Fluid fluid, int amount, int capacity) {
		this(new FluidStack(fluid, amount), capacity);
	}

	/**
	 * Called from {@link Entity#entityInit()}
	 * @param watcher The {@link DataWatcher} to use
	 */
	public void entityInit(DataWatcher watcher) {
		this.watcher = watcher;
		watcher.addObject(CAPACITY, 0);
		watcher.addObject(AMOUNT, 0);
		watcher.addObject(NAME, "");
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
		return watcher.getWatchableObjectString(NAME);
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
		watcher.updateObject(NAME, name);
	}

	private void setFluidAmount(int amount) {
		watcher.updateObject(AMOUNT, amount);
	}

	@Override
	public void setCapacity(int capacity) {
		watcher.updateObject(CAPACITY, capacity);
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
		return watcher.getWatchableObjectInt(AMOUNT);
	}

}
