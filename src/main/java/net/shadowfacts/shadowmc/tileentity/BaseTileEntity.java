package net.shadowfacts.shadowmc.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.nbt.AutoNBTSerializer;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.network.PacketUpdateTE;

import javax.annotation.Nonnull;

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

}
