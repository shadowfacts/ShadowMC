package net.shadowfacts.shadowmc.command;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandKillAll implements ISubCommand {

	public static CommandKillAll instance = new CommandKillAll();
	
	
	@Override
	public String getCommandName() {
	    return "killall";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) {
//		if (!CoreUtils.isOpOrServer(sender.getCommandSenderName())) {
//			sender.addChatMessage(new ChatComponentText(CommandHandler.COMMAND_DISALLOWED));
//			return;
//		}
		sender.addChatMessage(new ChatComponentText("This command is not implemented yet."));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new ChatComponentText("Kills all hostile mobs within a 32 block radius."));
	}

}
