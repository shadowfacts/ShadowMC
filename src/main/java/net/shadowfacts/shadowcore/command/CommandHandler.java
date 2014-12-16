package net.shadowfacts.shadowcore.command;

import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandHandler extends CommandBase {
	
	public static final String COMMAND_DISALLOWED = "You are not allowed to use this command.";
	
	public static CommandHandler instance = new CommandHandler();
	
	protected static TMap<String, ISubCommand> commands = new THashMap<String, ISubCommand>();
	
	
	static {
		registerSubCommand(CommandKillAll.instance);
		registerSubCommand(CommandVersion.instance);
		registerSubCommand(CommandHelp.instance);
	}
	
	
	public static void initCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(instance);
	}
	
	public static boolean registerSubCommand(ISubCommand subCommand) {
		if (!commands.containsKey(subCommand.getCommandName())) {
			commands.put(subCommand.getCommandName(), subCommand);
			return true;
		}
		return false;
	}
	
	public static Set<String> getCommandList() {
		return commands.keySet();
	}
	

	@Override
	public String getCommandName() {
		return "shadow";
	}
	
	@Override
	public List getCommandAliases() {
		return null;
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
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length <= 0) {
			throw new WrongUsageException("Type '" + getCommandUsage(sender) + "' for help.");
		}
		if (commands.containsKey(args[0])) {
			commands.get(args[0]).handleCommand(sender, args);
			return;
		}
		throw new WrongUsageException("Type '" + getCommandUsage(sender) + "' for help.");
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] par2StrArray) {
		if (par2StrArray.length == 1) {
			return getListOfStringsFromIterableMatchingLastWord(par2StrArray, commands.keySet());
		} else if (commands.containsKey(par2StrArray[0])) {
			return commands.get(par2StrArray[0]).addTabCompletionOptions(sender, par2StrArray);
		} else {
			return null;
		}
	}

}
