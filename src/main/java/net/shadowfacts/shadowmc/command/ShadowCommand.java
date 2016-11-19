package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

/**
 * @author shadowfacts
 */
public abstract class ShadowCommand extends CommandBase {

	public void handleHelp(ICommandSender sender) {
		sender.sendMessage(new TextComponentString(getUsage(sender)));
		addHelpMessage(sender);
	}

	protected abstract void addHelpMessage(ICommandSender sender);

}
