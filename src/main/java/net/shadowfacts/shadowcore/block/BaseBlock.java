package net.shadowfacts.shadowcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Base block class, should be used for all mod block classes.
 * @author shadowfacts
 */
public class BaseBlock extends Block {

	private final String blockName;


	public BaseBlock(String blockName) {
		this(blockName, Material.rock);
	}

	public BaseBlock(String blockName, Material material) {
		super(material);
		this.blockName = blockName;
	}

}
