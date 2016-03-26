package test;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.fluid.FluidTank;

/**
 * @author shadowfacts
 */
public class TileEntityTest extends TileEntity implements ITickable {

	public FluidTank tank = new FluidTank(new FluidStack(FluidRegistry.WATER, 250), 5000);

	@Override
	public void update() {
		if (tank.getFluidAmount() < tank.getCapacity()) {
			tank.setFluid(new FluidStack(FluidRegistry.WATER, tank.getFluidAmount() + 150));
		} else {
			tank.setFluid(new FluidStack(FluidRegistry.WATER, 250));
		}
	}

}
