package net.shadowfacts.shadowmc.command;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

/**
 * @author shadowfacts
 */
public class CommandShadow extends CommandTreeBase {

	public static final CommandShadow INSTANCE = new CommandShadow();

	private CommandShadow() {
		addSubcommand(CommandHelp.INSTANCE);
		addSubcommand(CommandKillAll.INSTANCE);
		addSubcommand(CommandGenStructure.INSTANCE);
		addSubcommand(CommandReloadStructures.INSTANCE);
	}

	@Override
	public String getName() {
		return "shadow";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow help";
	}

	public ShadowCommand getCommand(String name) {
		return (ShadowCommand)getCommandMap().get(name);
	}

}
