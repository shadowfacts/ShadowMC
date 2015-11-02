package net.shadowfacts.shadowmc.debug;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.util.coord.Coord3i;

import java.util.List;

/**
 * An debug that can be used to debug my blocks.
 * @author shadowfacts
 */
public class ItemDebugger extends Item {

	public ItemDebugger() {
		setUnlocalizedName("debugger");
		setTextureName("minecraft:missingno");
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

		Block block = world.getBlock(x, y, z);
		TileEntity te = world.getTileEntity(x, y, z);

		if (block instanceof IDebuggable) {
			printInfo(((IDebuggable)block).getDebugInfo(world, new Coord3i(x, y, z), player), player);
			return true;
		} else if (te instanceof IDebuggable) {
			printInfo(((IDebuggable)te).getDebugInfo(world, new Coord3i(x, y, z), player), player);
			return true;
		}

		return false;
	}


	private void printInfo(List<String> list, EntityPlayer player) {
		list.stream()
				.map(ChatComponentText::new)
				.forEach(player::addChatComponentMessage);
	}
}
