package net.shadowfacts.shadowmc.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public interface ISubCommand {
	
	public String getCommandName();
	
	public void handleCommand(ICommandSender sender, String[] args);
	
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args);

	public void handleHelpRequest(ICommandSender sender, String[] args);

}
