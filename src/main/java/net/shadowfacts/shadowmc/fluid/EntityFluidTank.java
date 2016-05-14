package net.shadowfacts.shadowmc.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author shadowfacts
 */
public class EntityFluidTank extends FluidTank {

	protected DataParameter<Integer> amount;
	protected DataParameter<Integer> capacity;
	protected DataParameter<String> name;

	protected EntityDataManager dataManager;

	public EntityFluidTank(EntityDataManager dataManager, DataParameter<Integer> amount, DataParameter<Integer> capacity, DataParameter<String> name, FluidStack stack, int tankCapacity) {
		super(tankCapacity);

		this.dataManager = dataManager;
		this.amount = amount;
		this.capacity = capacity;
		this.name = name;

		dataManager.register(capacity, 0);
		dataManager.register(amount, 0);
		dataManager.register(name, "");

		setCapacity(tankCapacity);
		setFluid(stack);
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
		return dataManager.get(name);
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
		dataManager.set(this.name, name);
	}

	private void setFluidAmount(int amount) {
		dataManager.set(this.amount, amount);
	}

	@Override
	public void setCapacity(int capacity) {
		dataManager.set(this.capacity, capacity);
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
		return dataManager.get(amount);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		if (!doFill) {
			if (getFluid() == null) {
				return Math.min(getCapacity(), resource.amount);
			}

			if (!getFluid().isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(getCapacity() - getFluidAmount(), resource.amount);
		}

		if (getFluid() == null) {
			setFluid(new FluidStack(resource, Math.min(getCapacity(), resource.amount)));

			return getFluidAmount();
		}

		if (!getFluid().isFluidEqual(resource)) {
			return 0;
		}
		int filled = getCapacity() - getFluidAmount();

		if (resource.amount < filled) {
			setFluidAmount(getFluidAmount() + resource.amount);
			filled = resource.amount;
		} else {
			setFluidAmount(getCapacity());
		}

		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (getFluid() == null) {
			return null;
		}

		int drained = maxDrain;
		if (getFluidAmount() < drained) {
			drained = getFluidAmount();
		}

		FluidStack stack = new FluidStack(getFluid(), drained);
		if (doDrain) {
			setFluidAmount(getFluidAmount() - drained);
			if (getFluidAmount() <= 0) {
				setFluid(null);
			}
		}
		return stack;
	}

}
