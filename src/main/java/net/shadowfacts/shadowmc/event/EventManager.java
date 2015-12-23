package net.shadowfacts.shadowmc.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.util.coord.Coord3f;
import net.shadowfacts.shadowmc.util.coord.Coord3i;

/**
 * @author shadowfacts
 */
public class EventManager {

	public void register(Object o) {
		FMLCommonHandler.instance().bus().register(o);
		MinecraftForge.EVENT_BUS.register(o);
	}

	public boolean post(Event e) {
		return MinecraftForge.EVENT_BUS.post(e);
	}

	public static boolean onToolUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return ShadowMC.bus.post(new ToolUseEvent(stack, player, world, new Coord3i(x, y, z), side, new Coord3f(hitX, hitY, hitZ)));
	}

}
