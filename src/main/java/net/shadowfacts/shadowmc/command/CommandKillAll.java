package net.shadowfacts.shadowmc.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandKillAll extends ShadowCommand {

	public static final CommandKillAll INSTANCE = new CommandKillAll();

	@Override
	public String getName() {
		return "killall";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/shadow killall <range>";
	}

	@Override
	protected void addHelpMessage(ICommandSender sender) {
		sender.sendMessage(new TextComponentString("Kills all mobs within a specified range."));
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		BlockPos pos = sender.getPosition();
		if (!pos.equals(new BlockPos(0, 0, 0))) {
			int range = 32;
			if (args.length == 1) {
				try {
					range = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					throw new WrongUsageException(getUsage(sender));
				}
			} else {
				throw new WrongUsageException(getUsage(sender));
			}

			world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range))
					.forEach(Entity::setDead);
		}
	}

}
