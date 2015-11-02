package net.shadowfacts.shadowmc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.shadowfacts.shadowmc.ShadowMC;

import java.util.ArrayList;
import java.util.List;

public class CommandVersion implements ISubCommand {

	public static CommandVersion instance = new CommandVersion();
	
	public List<String> versions = new ArrayList<>();

    public CommandVersion() {
        versions.add("ShadowMC " + EnumChatFormatting.GREEN + "v" + ShadowMC.version);
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
