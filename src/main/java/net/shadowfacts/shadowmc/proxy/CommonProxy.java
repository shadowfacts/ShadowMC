package net.shadowfacts.shadowmc.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.network.PacketSpawnItem;
import net.shadowfacts.shadowmc.network.PacketUpdateTE;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ShadowMC.network = NetworkRegistry.INSTANCE.newSimpleChannel(ShadowMC.modId);
		registerPackets();
	}

	private void registerPackets() {
		ShadowMC.network.registerMessage(PacketSpawnItem.class, PacketSpawnItem.class, 0, Side.SERVER);
		ShadowMC.network.registerMessage(PacketRequestTEUpdate.class, PacketRequestTEUpdate.class, 1, Side.SERVER);
		ShadowMC.network.registerMessage(PacketUpdateTE.class, PacketUpdateTE.class, 2, Side.CLIENT);
		ShadowMC.network.registerMessage(PacketUpdateTE.class, PacketUpdateTE.class, 2, Side.SERVER);
	}

	public World getClientWorld() {
		return null;
	}

	public void registerItemModel(Item item, int meta, ResourceLocation id) {

	}

}
