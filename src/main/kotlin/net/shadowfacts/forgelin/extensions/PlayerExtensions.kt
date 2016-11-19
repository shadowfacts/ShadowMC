package net.shadowfacts.forgelin.extensions

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentString
import net.shadowfacts.shadowmc.ShadowMC

/**
 * @author shadowfacts
 */
fun EntityPlayer.sendChatMsg(msg: String) {
	sendMessage(TextComponentString(msg))
}

fun EntityPlayer.sendChatMsg(msg: String, vararg params: Any) {
	sendChatMsg(msg.format(*params))
}

fun EntityPlayer.sendSpamlessChatMsg(msg: ITextComponent, id: Int) {
	ShadowMC.proxy.sendSpamlessMessage(this, msg, id)
}