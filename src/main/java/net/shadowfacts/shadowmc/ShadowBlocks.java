package net.shadowfacts.shadowmc;

import net.shadowfacts.shadowmc.block.ModBlocks;
import net.shadowfacts.shadowmc.structure.creator.BlockStructureCreator;

/**
 * @author shadowfacts
 */
public class ShadowBlocks extends ModBlocks {

	public BlockStructureCreator structureCreator;

	@Override
	public void init() {
		if (ShadowMCConfig.enableStructureCreator) {
			structureCreator = register(new BlockStructureCreator());
		}
	}

}
