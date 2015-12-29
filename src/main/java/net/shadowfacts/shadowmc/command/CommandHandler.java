package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.*;

public class CommandHandler extends CommandBase {
	
	public static final String COMMAND_DISALLOWED = "You are not allowed to use this command.";
	
	public static CommandHandler instance = new CommandHandler();
	
	protected static Map<String, SubCommand> commands = new HashMap<>();

	private static boolean initialized = false;
	
	static {
		registerSubCommand(CommandKillAll.instance);
		registerSubCommand(CommandHelp.instance);
		registerSubCommand(CommandReloadConfig.instance);
	}
	
	
	public static void initCommands(FMLServerStartingEvent event) {
		if (!initialized) {
			event.registerServerCommand(instance);
			initialized = true;
		}
	}

	public static boolean registerSubCommand(String name, SubCommand subCommand) {
		if (!commands.containsKey(name)) {
			commands.put(name, subCommand);
			return true;
		}
		return false;
	}

	public static boolean registerSubCommand(SubCommand subCommand) {
		return registerSubCommand(subCommand.getCommandName(), subCommand);
	}
	
	public static Set<String> getCommandList() {
		return commands.keySet();
	}
	

	@Override
	public String getCommandName() {
		return "shadow";
	}
	
	@Override
	public List<String> getCommandAliases() {
		return new ArrayList<>();
	}

	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return "/" + getCommandName() + " help";
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length <= 0) {
			throw new WrongUsageException("Type '" + getCommandUsage(sender) + "' for help.");
		}

		if (commands.containsKey(args[0])) {
			commands.get(args[0]).handleCommand(sender, Arrays.copyOfRange(args, 1, args.length));
		} else {
			throw new WrongUsageException("Type '" + getCommandUsage(sender) + "' for help.");
		}
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, commands.keySet());
		} else if (commands.containsKey(args[0])) {
			return commands.get(args[0]).addTabCompletionOptions(sender, args);
		} else {
			return null;
		}
	}

}
