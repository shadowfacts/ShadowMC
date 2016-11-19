package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowmc.structure.StructureManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandGenStructure extends ShadowCommand {

	public static final CommandGenStructure INSTANCE = new CommandGenStructure();

	@Override
	public String getName() {
		return "genStructure";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow genStructure <id>";
	}

	@Override
	protected void addHelpMessage(ICommandSender sender) {
		sender.sendMessage(new TextComponentString("Generates the given structure (must be registered) at the sender's position"));
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException(getUsage(sender));
		}
		BlockPos pos = sender.getPosition();
		if (sender.getCommandSenderEntity() != null) {
			pos = pos.offset(sender.getCommandSenderEntity().getHorizontalFacing());
		}
		StructureManager.INSTANCE.getValue(new ResourceLocation(args[0])).generate(sender.getEntityWorld(), pos);
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
		return StructureManager.INSTANCE.getKeys().stream()
				.map(ResourceLocation::toString)
				.collect(Collectors.toList());
	}

}
