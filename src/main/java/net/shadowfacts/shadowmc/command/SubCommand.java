package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.List;

public interface SubCommand {
	
	String getCommandName();
	
	void handleCommand(ICommandSender sender, String[] args) throws CommandException;
	
	List<String> addTabCompletionOptions(ICommandSender sender, String[] args);

	void handleHelpRequest(ICommandSender sender, String[] args);

}
