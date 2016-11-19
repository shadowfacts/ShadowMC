package net.shadowfacts.shadowmc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.shadowfacts.shadowmc.item.ItemOreDict;
import net.shadowfacts.shadowmc.item.ModItems;

/**
 * @author shadowfacts
 */
public class ShadowItems extends ModItems {

	ItemOreDict nuggetIron;

	@Override
	public void init() {
		if (ShadowMCConfig.enableNuggetIron && !OreDictionary.doesOreNameExist("nuggetIron")) {
			nuggetIron = register((ItemOreDict)new ItemOreDict("nugget_iron", "nuggetIron").setCreativeTab(CreativeTabs.MATERIALS));
		}
	}

	public ItemStack getNuggetIron() {
		if (OreDictionary.doesOreNameExist("nuggetIron")) {
			return OreDictionary.getOres("nuggetIron").get(0);
		} else {
			return new ItemStack(nuggetIron);
		}
	}

}
