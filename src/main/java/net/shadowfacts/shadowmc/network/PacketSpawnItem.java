package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.shadowfacts.shadowmc.util.Vector3d;

/**
 * @author shadowfacts
 */
@NoArgsConstructor
@AllArgsConstructor
public class PacketSpawnItem extends PacketBase<PacketSpawnItem, IMessage> {

	public ItemStack stack;
	public int dim;
	public Vector3d pos;

	@Override
	public IMessage onMessage(PacketSpawnItem msg, MessageContext ctx) {
		MinecraftServer.getServer().addScheduledTask(() -> {
			World world = MinecraftServer.getServer().worldServerForDimension(msg.dim);
			EntityItem entity = new EntityItem(world, msg.pos.x, msg.pos.y, msg.pos.z, msg.stack);
			entity.setDefaultPickupDelay();
			world.spawnEntityInWorld(entity);
		});
		return null;
	}

}
