package net.shadowfacts.shadowmc.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author shadowfacts
 */
@Getter
@Setter
@AllArgsConstructor
@Cancelable
public class ToolUseEvent extends Event {

	private ItemStack toolStack;
	private EntityPlayer player;
	private World world;
	private BlockPos blockHit;
	private EnumFacing side;
	private Vec3 hit;

}
