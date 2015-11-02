package net.shadowfacts.shadowmc.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.util.coord.Coord3i;

import java.util.List;

/**
 * An interface that should be used for any blocks/tile entities that can be debugged.
 * @author shadowfacts
 */
public interface IDebuggable {

	List<String> getDebugInfo(World world, Coord3i coord, EntityPlayer player);

}
