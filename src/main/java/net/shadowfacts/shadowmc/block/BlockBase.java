package net.shadowfacts.shadowmc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.item.ItemModelProvider;

/**
 * @author shadowfacts
 */
public class BlockBase extends Block implements ItemModelProvider {

	public final String name;

	public BlockBase(Material material, String name) {
		super(material);

		this.name = name;

		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
	}


	@Override
	public void initItemModel() {
		Item item = Item.getItemFromBlock(this);
		if (item != null) {
			ShadowMC.proxy.registerItemModel(item, 0, getRegistryName());
		}
	}

}
