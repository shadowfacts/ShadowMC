package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CommandKillAll implements SubCommand {

	public static CommandKillAll instance = new CommandKillAll();

	@Override
	public String getCommandName() {
	    return "killall";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		BlockPos pos = sender.getPosition();
		if (!pos.equals(new BlockPos(0, 0, 0))) {
			int range = 32;
			if (args.length == 1) {
				try {
					range = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					throw new WrongUsageException("Range must be an integer!");
				}
			} else {
				throw new WrongUsageException("Incorrect number of arguments, see /shadow help killall for more info");
			}


			world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range))
					.stream()
					.forEach(Entity::setDead);
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return new ArrayList<>();
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new TextComponentString("Kills all mobs within a specified range."));
	}

}
