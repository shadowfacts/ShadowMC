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

/**
 * @author shadowfacts
 */
public abstract class BaseTileEntity extends TileEntity {

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		AutoNBTSerializer.serialize(getClass(), this, compound);
		save(compound, true);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		AutoNBTSerializer.deserialize(getClass(), this, compound);
		load(compound, true);
	}

	public abstract NBTTagCompound save(NBTTagCompound tag, boolean saveInventory);

	public abstract void load(NBTTagCompound tag, boolean loadInventory);

	public void sync() {
		if (getWorld().isRemote) {
			ShadowMC.network.sendToServer(new PacketUpdateTE(this));
		} else {
			ShadowMC.network.sendToAllAround(new PacketUpdateTE(this), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 64));
		}
	}

}
