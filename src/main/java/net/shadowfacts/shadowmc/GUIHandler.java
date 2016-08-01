package net.shadowfacts.shadowmc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.shadowfacts.shadowmc.inventory.ContainerPlayerInv;
import net.shadowfacts.shadowmc.structure.creator.GUIStructureCreator;
import net.shadowfacts.shadowmc.structure.creator.TileEntityStructureCreator;

/**
 * @author shadowfacts
 */
public class GUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
			case 0:
				if (ShadowMCConfig.enableStructureCreator) {
					return new ContainerPlayerInv(pos, player.inventory);
				}
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
			case 0:
				if (ShadowMCConfig.enableStructureCreator) {
					return GUIStructureCreator.create(pos, player.inventory, (TileEntityStructureCreator)world.getTileEntity(pos));
				}
			default:
				return null;
		}
	}

}
