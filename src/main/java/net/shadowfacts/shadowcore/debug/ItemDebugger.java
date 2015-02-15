package net.shadowfacts.shadowcore.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * An debug that can be used to debug my blocks.
 * @author shadowfacts
 */
public class ItemDebugger extends Item {

	public ItemDebugger() {
		super();
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (world.getBlock(x, y, z) instanceof IDebuggable) {
			IDebuggable block = (IDebuggable)world.getBlock(x, y, z);

			for (String s : block.getDebugInfo()) {
				player.addChatMessage(new ChatComponentText(s));
			}

			return true;
		} else if (world.getTileEntity(x, y, z) instanceof IDebuggable) {
			IDebuggable tileEntity = (IDebuggable)world.getTileEntity(x, y, z);

			for (String s : tileEntity.getDebugInfo()) {
				player.addChatMessage(new ChatComponentText(s));
			}

			return true;
		}

		return false;
	}
}
