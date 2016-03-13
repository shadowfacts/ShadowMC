package net.shadowfacts.shadowmc.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author shadowfacts
 */
public class CreativeFluidTank extends FluidTank {

	public CreativeFluidTank(int capacity) {
		super(capacity);
	}

	public CreativeFluidTank(FluidStack stack, int capacity) {
		super(stack, capacity);
	}

	public CreativeFluidTank(Fluid fluid, int amount, int capacity) {
		super(fluid, amount, capacity);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (fluid == null) {
			fluid = resource.copy();
			return resource.amount;
		}
		if (fluid.isFluidEqual(resource)) {
			fluid.amount += resource.amount;
			return resource.amount;
		}
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) {
			return null;
		}
		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}
		return new FluidStack(fluid, drained);
	}

}
