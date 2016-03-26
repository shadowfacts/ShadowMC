package test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author shadowfacts
 */
@Mod(modid = ModTest.modId, name = ModTest.name, version = ModTest.version)
public class ModTest {

	public static final String modId = "modTest";
	public static final String name = "Mod Test";
	public static final String version = "0.1.0";

	@Mod.Instance("modTest")
	public static ModTest instance;

	private BlockTest blockTest;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TestGUIHandler());

		blockTest = new BlockTest();
		GameRegistry.registerBlock(blockTest, "blockTest");

		GameRegistry.registerTileEntity(TileEntityTest.class, "tileEntity");
	}

	public static class TestGUIHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				default:
					return null;
			}
		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				case 0:
					return GUITest.create((TileEntityTest)world.getTileEntity(new BlockPos(x, y, z)));
				default:
					return null;
			}
		}
	}

}
