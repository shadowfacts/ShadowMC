package net.shadowfacts.shadowmc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.item.ItemModelProvider;

/**
 * @author shadowfacts
 */
public class BlockBase extends Block implements ItemModelProvider, ItemBlockProvider {

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

	@Override
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

}
