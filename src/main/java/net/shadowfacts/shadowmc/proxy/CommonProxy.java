package net.shadowfacts.shadowmc.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.flair.FlairManager;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.network.PacketSpamlessMessage;
import net.shadowfacts.shadowmc.network.PacketUpdateTE;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ShadowMC.network = NetworkRegistry.INSTANCE.newSimpleChannel(ShadowMC.modId);
		registerPackets();
	}

	public void init(FMLInitializationEvent event) {
		FlairManager.initCommon();
	}

	private void registerPackets() {
		ShadowMC.network.registerMessage(PacketRequestTEUpdate.class, PacketRequestTEUpdate.class, 0, Side.SERVER);
		ShadowMC.network.registerMessage(PacketUpdateTE.class, PacketUpdateTE.class, 1, Side.CLIENT);
		ShadowMC.network.registerMessage(PacketSpamlessMessage.class, PacketSpamlessMessage.class, 2, Side.CLIENT);
	}

	public World getClientWorld() {
		return null;
	}

	public EntityPlayer getClientPLayer() {
		return null;
	}

	public void registerItemModel(Item item, int meta, ResourceLocation id) {

	}

	public void sendSpamlessMessage(EntityPlayer player, ITextComponent msg, int id) {
		if (player instanceof EntityPlayerMP) {
			ShadowMC.network.sendTo(new PacketSpamlessMessage(msg, id), (EntityPlayerMP)player);
		}
	}
}
