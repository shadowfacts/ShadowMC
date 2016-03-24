package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
@NoArgsConstructor
@AllArgsConstructor
public class PacketRequestTEUpdate extends PacketBase<PacketRequestTEUpdate, IMessage> {

	public int dim;
	public BlockPos pos;

	public PacketRequestTEUpdate(BaseTileEntity te) {
		this(te.getWorld().provider.getDimensionId(), te.getPos());
	}

	@Override
	public IMessage onMessage(PacketRequestTEUpdate msg, MessageContext ctx) {
		int dim = msg.dim;
		BlockPos pos = msg.pos;
		MinecraftServer.getServer().addScheduledTask(() -> {
			ShadowMC.network.sendToAllAround(new PacketUpdateTE((BaseTileEntity)MinecraftServer.getServer().worldServerForDimension(dim).getTileEntity(pos)), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), 64));
		});
		return null;
	}

}
