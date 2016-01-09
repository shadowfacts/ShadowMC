package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class CommandHelp implements SubCommand {
	public static CommandHelp instance = new CommandHelp();

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			if (args.length == 1) {
				StringBuilder output = new StringBuilder();
				List<String> commandList = new ArrayList<>(CommandHandler.getCommandList());

				commandList.stream()
						.map(s -> EnumChatFormatting.YELLOW + "/shadow " + s + EnumChatFormatting.WHITE + ", ")
						.forEach(output::append);

				output.delete(output.length() - 2, output.length()); // remove the last 2 characters (the ending ", ")

				sender.addChatMessage(new ChatComponentText(output.toString()));
			} else if (args.length > 1) {
				CommandHandler.commands.get(args[1]).handleHelpRequest(sender, args);
			}
		}
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 2) {
			return new ArrayList<>(CommandHandler.commands.keySet());
		}
		return null;
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new ChatComponentText("Help info about all " + EnumChatFormatting.YELLOW + "/shadow" + EnumChatFormatting.WHITE + " commands."));
	}
			
}
