package net.shadowfacts.shadowmc.flair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

/**
 * @author shadowfacts
 */
public class NickFormatFlair extends Flair {

	private final String format;

	public NickFormatFlair(UUID uuid, TextFormatting... formatting) {
		super(uuid);
		StringBuilder builder = new StringBuilder();
		for (TextFormatting format : formatting) {
			builder.append(format);
		}
		this.format = builder.toString();
	}

	@Override
	public void initClient() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onNickFormat(PlayerEvent.NameFormat event) {
		if (EntityPlayer.getUUID(event.getEntityPlayer().getGameProfile()).equals(uuid)) {
			event.setDisplayname(format + event.getDisplayname());
		}
	}

}
