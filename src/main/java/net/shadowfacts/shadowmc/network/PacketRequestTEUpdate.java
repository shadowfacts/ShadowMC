package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
		this(te.getWorld().provider.getDimension(), te.getPos());
	}

	@Override
	public PacketUpdateTE onMessage(PacketRequestTEUpdate msg, MessageContext ctx) {
		return new PacketUpdateTE(((BaseTileEntity)FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(msg.dim).getTileEntity(msg.pos)));
	}

}
