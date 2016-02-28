package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
@NoArgsConstructor
@AllArgsConstructor
public class PacketUpdateTE extends PacketBase<PacketUpdateTE, IMessage> {

	public int dim;
	public BlockPos pos;
	public NBTTagCompound tag;

	public PacketUpdateTE(BaseTileEntity te) {
		this(te.getWorld().provider.getDimensionId(), te.getPos(), null);
		NBTTagCompound tag = new NBTTagCompound();
		te.writeToNBT(tag);
		this.tag = tag;
	}

	@Override
	public IMessage onMessage(PacketUpdateTE msg, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			World world = ShadowMC.proxy.getClientWorld();
			TileEntity te = world.getTileEntity(msg.pos);
			if (te instanceof BaseTileEntity) {
				te.readFromNBT(msg.tag);
			}
		} else {
			World world = MinecraftServer.getServer().worldServerForDimension(msg.dim);
			TileEntity te = world.getTileEntity(msg.pos);
			if (te instanceof BaseTileEntity) {
				te.readFromNBT(msg.tag);
			}
		}
		return null;
	}

}
