package net.shadowfacts.shadowmc.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldServer;

import java.util.stream.Stream;

/**
 * @author shadowfacts
 */
public class PlayerUtils {

	public static void teleportPlayer(EntityPlayerMP player, int x, int y, int z, int dim) {
		if (player.dimension != dim) {
			int old = player.dimension;
			player.dimension = dim;

			WorldServer oldWorld = player.mcServer.worldServerForDimension(old);
			WorldServer newWorld = player.mcServer.worldServerForDimension(dim);

			player.playerNetServerHandler.sendPacket(new S07PacketRespawn(player.dimension, player.worldObj.difficultySetting, newWorld.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));
			oldWorld.removePlayerEntityDangerously(player);
			player.isDead = false;

			if(player.isEntityAlive())
			{
				newWorld.spawnEntityInWorld(player);
				player.setLocationAndAngles(x + 0.5, y + 1, z + 0.5, player.rotationYaw, player.rotationPitch);
				newWorld.updateEntityWithOptionalForce(player, false);
				player.setWorld(newWorld);
			}

			player.mcServer.getConfigurationManager().func_72375_a(player, oldWorld);
			player.playerNetServerHandler.setPlayerLocation(x + 0.5, y + 1, z + 0.5, player.rotationYaw, player.rotationPitch);
			player.theItemInWorldManager.setWorld(newWorld);
			player.mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, newWorld);
			player.mcServer.getConfigurationManager().syncPlayerInventory(player);

			((Stream<PotionEffect>) player.getActivePotionEffects().stream())
					.map(potion -> new S1DPacketEntityEffect(player.getEntityId(), potion))
					.forEach(player.playerNetServerHandler::sendPacket);

			player.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));

			FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, old, dim);
		}
		else {
			player.playerNetServerHandler.setPlayerLocation(x + 0.5, y + 1, z + 0.5, player.rotationYaw, player.rotationPitch);
		}

		// TODO: TEST

	}

	public static void addChatMsg(EntityPlayer player, String msg) {
		player.addChatComponentMessage(new ChatComponentText(msg));
	}

	public static void addChatMsg(EntityPlayer player, String msg, Object... params) {
		addChatMsg(player, String.format(msg, params));
	}

}
