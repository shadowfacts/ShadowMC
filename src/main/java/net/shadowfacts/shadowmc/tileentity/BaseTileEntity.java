package net.shadowfacts.shadowmc.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.network.PacketUpdateTE;

/**
 * @author shadowfacts
 */
public abstract class BaseTileEntity extends TileEntity {

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	public abstract NBTTagCompound save(NBTTagCompound tag, boolean saveInventory);

	public abstract void load(NBTTagCompound tag, boolean loadInventory);

	public void sync() {
		Side side = FMLCommonHandler.instance().getSide();
		if (side == Side.CLIENT) {
			ShadowMC.network.sendToServer(new PacketUpdateTE(this));
		} else {
			ShadowMC.network.sendToAllAround(new PacketUpdateTE(this), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 64));
		}
	}

}
