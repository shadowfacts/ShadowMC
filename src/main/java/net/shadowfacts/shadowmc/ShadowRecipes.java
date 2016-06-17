package net.shadowfacts.shadowmc;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author shadowfacts
 */
public class ShadowRecipes {

	public static void init() {
		if (ShadowMCConfig.enableNuggetIron && ShadowMC.items.nuggetIron != null) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ShadowMC.items.nuggetIron, 9), "ingotIron"));
			GameRegistry.addShapedRecipe(new ItemStack(Items.IRON_INGOT), "iii", "iii", "iii", 'i', ShadowMC.items.nuggetIron);
		}
	}

}
