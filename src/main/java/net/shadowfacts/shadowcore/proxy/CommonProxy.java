package net.shadowfacts.shadowcore.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.server.MinecraftServer;

public class CommonProxy {



	public boolean isOp(String playerName) {
		MinecraftServer theServer = FMLCommonHandler.instance().getMinecraftServerInstance();
		playerName = playerName.trim();
		for (String a: theServer.getConfigurationManager().func_152606_n()) {
			if (playerName.equalsIgnoreCase(playerName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isClient() {
		return false;
	}

	public boolean isServer() {
		return true;
	}
}
