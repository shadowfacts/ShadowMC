package net.shadowfacts.shadowcore.debug;

import net.minecraft.block.BlockLever;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.shadowfacts.shadowcore.ShadowCore;
import net.shadowfacts.shadowcore.util.StringHelper;

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
		} else if (world.getBlock(x, y, z) instanceof BlockLever) {
			String msg = "";

			if (world.getBlockMetadata(x, y, z) == 5) {
				msg = StringHelper.BRIGHT_GREEN + "Lever" + StringHelper.WHITE + ": " + StringHelper.LIGHT_RED + "idle";
			} else if (world.getBlockMetadata(x, y, z) == 13) {
				msg = StringHelper.BRIGHT_GREEN + "Lever" + StringHelper.WHITE + ": " + StringHelper.LIGHT_RED + "active";
			}

			if (msg != "" ) {
				player.addChatMessage(new ChatComponentText(msg));
			}

		}

		return true;
	}

//	@Override
//	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
//		player.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_GREEN + player.getDisplayName() + StringHelper.WHITE));
//		player.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_BLUE + "Experience" + StringHelper.WHITE + ": " + player.experience));
//		player.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_BLUE + "Health" + StringHelper.WHITE + ": " + player.getHealth()));
//		player.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_BLUE + "Hunger" + StringHelper.WHITE + ": " + player.getFoodStats().getFoodLevel()));
//		player.addChatMessage(new ChatComponentText(StringHelper.BRIGHT_BLUE + "Armor" + StringHelper.WHITE + ": " + player.getTotalArmorValue()));
//
//		return itemStack;
//	}
}
