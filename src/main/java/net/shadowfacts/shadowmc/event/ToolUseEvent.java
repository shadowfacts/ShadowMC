package net.shadowfacts.shadowmc.event;

import cpw.mods.fml.common.eventhandler.Event;
import lombok.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.util.coord.Coord3f;
import net.shadowfacts.shadowmc.util.coord.Coord3i;

/**
 * @author shadowfacts
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToolUseEvent extends Event {

	private ItemStack toolStack;
	private EntityPlayer player;
	private World world;
	private Coord3i blockHit;
	private int side;
	private Coord3f hit;

	@Override
	public boolean isCancelable() {
		return true;
	}
}
