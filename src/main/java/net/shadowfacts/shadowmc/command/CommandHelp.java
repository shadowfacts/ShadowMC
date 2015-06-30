package net.shadowfacts.shadowmc.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import net.shadowfacts.shadowmc.util.StringHelper;

public class CommandHelp implements ISubCommand {
	public static CommandHelp instance = new CommandHelp();

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) {
		if (args.length == 0) {
			return;
		} else if (args.length == 1) {
			StringBuilder output = new StringBuilder();
			List<String> commandList = new ArrayList<String>(CommandHandler.getCommandList());

			for (int i = 0; i < commandList.size(); i++) {
				output.append(StringHelper.YELLOW + "/shadow " + commandList.get(i) + StringHelper.WHITE + ", ");
			}

			output.delete(output.length() - 2, output.length()); // remove the last 2 characters (the ending ", ")

			sender.addChatMessage(new ChatComponentText(output.toString()));

		} else if (args.length > 1) {
			CommandHandler.commands.get(args[1]).handleHelpRequest(sender, args);
		}
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new ChatComponentText("Help info about all " + StringHelper.YELLOW + "/shadow" + StringHelper.WHITE + " commands."));
	}
			
}
