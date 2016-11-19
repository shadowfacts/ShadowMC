package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowmc.structure.StructureManager;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandReloadStructures extends ShadowCommand {

	public static final CommandReloadStructures INSTANCE = new CommandReloadStructures();

	@Override
	public String getName() {
		return "reloadStructures";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow reloadStructures";
	}

	@Override
	protected void addHelpMessage(ICommandSender sender) {
		sender.sendMessage(new TextComponentString("Reloads all structures registered with the StructureManager"));
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		StructureManager.INSTANCE.reload();
		sender.sendMessage(new TextComponentString("Reloaded structures"));
	}

}
