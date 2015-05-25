package net.shadowfacts.shadowapi.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.shadowfacts.shadowapi.ShadowAPIProps;

import java.util.ArrayList;
import java.util.List;

public class CommandVersion implements ISubCommand {

	public static CommandVersion instance = new CommandVersion();
	
	public ArrayList<String> versions = new ArrayList<String>();

    public CommandVersion() {
        versions.add("ShadowAPI v" + ShadowAPIProps.version);
    }

	@Override
	public String getCommandName() {
		return "version";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) {
		for (String s : versions) {
			sender.addChatMessage(new ChatComponentText(s));
		}
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
