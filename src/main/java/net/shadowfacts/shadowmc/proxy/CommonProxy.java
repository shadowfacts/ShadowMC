package net.shadowfacts.shadowmc.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.network.PacketSpawnItem;

/**
 * @author shadowfacts
 */
public class CommonProxy extends BaseProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ShadowMC.network = NetworkRegistry.INSTANCE.newSimpleChannel(ShadowMC.modId);
		registerPackets();
	}

	@Override
	public void init(FMLInitializationEvent event) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

	private void registerPackets() {
		ShadowMC.network.registerMessage(PacketSpawnItem.class, PacketSpawnItem.class, 0, Side.SERVER);
	}

}
