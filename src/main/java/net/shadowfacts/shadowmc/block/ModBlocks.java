package net.shadowfacts.shadowmc.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.shadowmc.item.OreDictItem;

/**
 * @author shadowfacts
 */
public abstract class ModBlocks {

	public abstract void init();

	protected <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

	protected <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);

		if (itemBlock != null) {
			GameRegistry.register(itemBlock);
		}

		if (block instanceof ItemModelProvider) {
			((ItemModelProvider)block).initItemModel();
		}
		if (block instanceof OreDictItem) {
			((OreDictItem)block).registerOreDict();
		}
		if (block instanceof BlockTE) {
			GameRegistry.registerTileEntity(((BlockTE<?>)block).getTileEntityClass(), ((BlockBase)block).name);
		} else if (block instanceof TileEntityProvider) {
			GameRegistry.registerTileEntity(((TileEntityProvider<?>)block).getTileEntityClass(), block.getRegistryName().toString());
		}

		return block;
	}

}
