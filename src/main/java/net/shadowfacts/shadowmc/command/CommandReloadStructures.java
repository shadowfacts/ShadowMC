package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowmc.structure.StructureManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandReloadStructures implements SubCommand {

	public static final CommandReloadStructures instance = new CommandReloadStructures();

	@Override
	public String getCommandName() {
		return "reloadStructures";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) throws CommandException {
		StructureManager.INSTANCE.reload();
		sender.addChatMessage(new TextComponentString("Reloaded Structures"));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return new ArrayList<>();
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new TextComponentString("Reloads all structures registered with the StructureManager"));
	}

}
