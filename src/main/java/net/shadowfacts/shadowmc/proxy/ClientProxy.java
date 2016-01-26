package net.shadowfacts.shadowmc.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}
