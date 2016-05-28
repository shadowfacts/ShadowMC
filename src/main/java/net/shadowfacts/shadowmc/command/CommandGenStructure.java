package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowmc.structure.StructureManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandGenStructure implements SubCommand {

	public static final CommandGenStructure instance = new CommandGenStructure();

	@Override
	public String getCommandName() {
		return "genStructure";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) throws CommandException {
		StructureManager.INSTANCE.getValue(new ResourceLocation(args[0])).generate(sender.getEntityWorld(), sender.getPosition().offset(sender.getCommandSenderEntity().getHorizontalFacing()));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return StructureManager.INSTANCE.getKeys().stream()
				.map(ResourceLocation::toString)
				.collect(Collectors.toList());
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new TextComponentString("Generates the given structure (must be registered) at the sender's position"));
	}

}
