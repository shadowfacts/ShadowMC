package net.shadowfacts.shadowmc.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.capability.CapHelper;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.nbt.AutoNBTSerializer;
import net.shadowfacts.shadowmc.network.PacketUpdateTE;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author shadowfacts
 */
public abstract class BaseTileEntity extends TileEntity {

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		AutoNBTSerializer.serialize(getClass(), this, tag);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		AutoNBTSerializer.deserialize(getClass(), this, tag);
	}

	public void sync() {
		if (getWorld().isRemote) {
			ShadowMC.network.sendToServer(new PacketUpdateTE(this));
		} else {
			ShadowMC.network.sendToAllAround(new PacketUpdateTE(this), new NetworkRegistry.TargetPoint(worldObj.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return CapHelper.hasCapability(capability, facing, this) || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return CapHelper.getCapability(capability, facing, this, (capability1, enumFacing) -> (T)super.getCapability(capability1, enumFacing));
	}

}
