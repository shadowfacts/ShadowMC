package net.shadowfacts.shadowmc.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class FluidTank extends net.minecraftforge.fluids.FluidTank {

	public FluidTank(int capacity) {
		super(capacity);
	}

	public FluidTank(@Nullable FluidStack fluidStack, int capacity) {
		super(fluidStack, capacity);
	}

	public FluidTank(Fluid fluid, int amount, int capacity) {
		super(fluid, amount, capacity);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("capacity", capacity);
		return nbt;
	}

	@Override
	public FluidTank readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		capacity = nbt.getInteger("capacity");
		return this;
	}

}
