package net.shadowfacts.shadowmc.network;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.shadowfacts.shadowmc.ShadowMC;

/**
 * @author shadowfacts
 */
@NoArgsConstructor
@AllArgsConstructor
public class PacketSpamlessMessage extends PacketBase<PacketSpamlessMessage, IMessage> {

	private ITextComponent msg;
	private int id;

	@Override
	public IMessage onMessage(PacketSpamlessMessage message, MessageContext ctx) {
		ShadowMC.proxy.sendSpamlessMessage(Minecraft.getMinecraft().thePlayer, message.msg, message.id);
		return null;
	}

}
