package net.shadowfacts.shadowmc.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;

/**
 * @author shadowfacts
 */
@AllArgsConstructor
public class ContainerBase extends Container {

	@Getter
	protected BlockPos pos;

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return player.getDistanceSq((double) pos.getX() + .5d, (double) pos.getY() + .5d, (double) pos.getZ() + .5d) <= 64;
	}

}
