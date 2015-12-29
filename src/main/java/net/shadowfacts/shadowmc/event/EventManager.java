package net.shadowfacts.shadowmc.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.shadowfacts.shadowmc.ShadowMC;

/**
 * @author shadowfacts
 */
public class EventManager {

	public void register(Object o) {
		MinecraftForge.EVENT_BUS.register(o);
	}

	public boolean post(Event e) {
		return MinecraftForge.EVENT_BUS.post(e);
	}

	public static boolean onToolUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		return ShadowMC.bus.post(new ToolUseEvent(stack, player, world, pos, side, new Vec3(hitX, hitY, hitZ)));
	}

}
