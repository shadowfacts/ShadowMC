package net.shadowfacts.shadowcore.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.shadowfacts.shadowcore.ShadowCore;
import net.shadowfacts.shadowcore.util.StringHelper;

import java.util.List;

public class CommandVersion implements ISubCommand {

	public static CommandVersion instance = new CommandVersion();
	
	
	@Override
	public String getCommandName() {
		return "version";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) {
		sender.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_GREEN + "ShadowCore:" + StringHelper.WHITE + " v" + ShadowCore.version));
//		sender.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_GREEN + "EnFusion:" + StringHelper.WHITE + " v" + EnFusion.version));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new ChatComponentText("Displays version info."));
	}

}
