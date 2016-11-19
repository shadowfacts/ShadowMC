package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandHelp extends ShadowCommand {

	public static final CommandHelp INSTANCE = new CommandHelp();

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow help <command>";
	}

	@Override
	protected void addHelpMessage(ICommandSender sender) {
		sender.sendMessage(new TextComponentString(String.format("Help info about all %s/shadow%s commands.", TextFormatting.YELLOW, TextFormatting.WHITE)));
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException(getUsage(sender));
		}

		CommandShadow.INSTANCE.getCommand(args[0]).handleHelp(sender);
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
		return new ArrayList<>(CommandShadow.INSTANCE.getCommandMap().keySet());
	}
}
