package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;

/**
 * @author shadowfacts
 */
@NoArgsConstructor
@AllArgsConstructor
public class PacketRequestTEUpdate extends PacketBase<PacketRequestTEUpdate, PacketUpdateTE> {

	public int dim;
	public BlockPos pos;

	public PacketRequestTEUpdate(BaseTileEntity te) {
		this(te.getWorld().provider.getDimensionId(), te.getPos());
	}

	@Override
	public PacketUpdateTE onMessage(PacketRequestTEUpdate msg, MessageContext ctx) {
		return new PacketUpdateTE(((BaseTileEntity) MinecraftServer.getServer().worldServerForDimension(msg.dim).getTileEntity(msg.pos)));
	}

}
