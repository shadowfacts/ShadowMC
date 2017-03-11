package net.shadowfacts.shadowmc.command;

import com.udojava.evalex.Expression;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandCalc extends ShadowCommand {

	public static final CommandCalc INSTANCE = new CommandCalc();

	@Override
	public String getName() {
		return "calc";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow calc <expr>";
	}

	@Override
	protected void addHelpMessage(ICommandSender sender) {
		sender.sendMessage(new TextComponentString("Evaluates the given mathematical expression"));
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			throw new WrongUsageException(getUsage(sender));
		}
		try {
			sender.sendMessage(new TextComponentString("Result: " + new Expression(String.join(" ", args)).eval()));
		} catch (Expression.ExpressionException e) {
			throw new CommandException(e.getMessage());
		}
	}

}
