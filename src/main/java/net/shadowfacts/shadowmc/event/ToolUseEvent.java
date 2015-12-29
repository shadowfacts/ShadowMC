package net.shadowfacts.shadowmc.event;

import lombok.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author shadowfacts
 */
@Getter
@Setter
@AllArgsConstructor
public class ToolUseEvent extends Event {

	private ItemStack toolStack;
	private EntityPlayer player;
	private World world;
	private BlockPos blockHit;
	private EnumFacing side;
	private Vec3 hit;

	@Override
	public boolean isCancelable() {
		return true;
	}
}
