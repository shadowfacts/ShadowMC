package net.shadowfacts.shadowmc.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.BlockPos;

/**
 * @author shadowfacts
 */
public class ContainerPlayerInv extends ContainerBase {

	public ContainerPlayerInv(BlockPos pos, InventoryPlayer playerInv) {
		super(pos);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}

}
