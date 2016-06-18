package net.shadowfacts.shadowmc.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public void registerItemModel(Item item, int meta, ResourceLocation id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(id, "inventory"));
	}

}
