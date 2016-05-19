package net.shadowfacts.shadowmc.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * @author shadowfacts
 */
public class ItemBase extends Item implements ItemModelProvider {

	protected final String name;

	public ItemBase(String name) {
		this.name = name;

		setUnlocalizedName(name);
		setRegistryName(name);
	}


	@Override
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
