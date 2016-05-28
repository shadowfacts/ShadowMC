package net.shadowfacts.shadowmc.structure;

import net.minecraft.util.ResourceLocation;

/**
 * @author shadowfacts
 */
@FunctionalInterface
public interface IStructureReloadHandler {

	/**
	 * Reloads the structure
	 * @param name The name of the structure
	 * @return The new structure, {@code null} if the old value shouldn't be replaced
	 */
	Structure reload(ResourceLocation name);

}
