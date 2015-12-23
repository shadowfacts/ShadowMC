package net.shadowfacts.shadowmc.command;

import net.minecraft.command.ICommandSender;

import java.util.List;

public interface ISubCommand {
	
	public String getCommandName();
	
	public void handleCommand(ICommandSender sender, String[] args);
	
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args);

	public void handleHelpRequest(ICommandSender sender, String[] args);

}
