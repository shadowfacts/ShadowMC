package net.shadowfacts.shadowmc.util;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
public interface MeshWrapper extends ItemMeshDefinition {

	static MeshWrapper of(MeshWrapper wrapper) {
		return wrapper;
	}

	ModelResourceLocation getLocation(ItemStack stack);

	@Override
	default ModelResourceLocation getModelLocation(ItemStack stack) {
		return getLocation(stack);
	}

}
