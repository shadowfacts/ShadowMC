package net.shadowfacts.shadowcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Base block class, should be used for all mod block classes.
 * @author shadowfacts
 */
public class BaseBlock extends Block {

	private final String modId;
	private final String blockName;


	public BaseBlock(String modId, String blockName) {
		this(modId, blockName, Material.rock);
	}

	public BaseBlock(String modId, String blockName, Material material) {
		super(material);
		this.modId = modId;
		this.blockName = blockName;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s.%s", modId, blockName);
	}

	@Override
	protected String getTextureName() {
		return blockName;
	}

}
