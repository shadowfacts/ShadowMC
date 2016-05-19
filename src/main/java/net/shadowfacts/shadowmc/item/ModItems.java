package net.shadowfacts.shadowmc.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author shadowfacts
 */
public abstract class ModItems {

	public abstract void init();

	protected <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider)item).initItemModel();
		}
		if (item instanceof OreDictItem) {
			((OreDictItem)item).registerOreDict();
		}

		return item;
	}

}
