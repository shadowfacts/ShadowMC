package test;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.shadowmc.inventory.ContainerPlayerInv;
import net.shadowfacts.shadowmc.ui.element.button.*;
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIContainerWrapper;
import net.shadowfacts.shadowmc.ui.style.*;
import test.ui.dsl.UIDSLTestKt;

import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
@Mod(modid = ModTest.modId, name = ModTest.name, version = ModTest.version)
public class ModTest {

	public static final String modId = "modtest";
	public static final String name = "Mod Test";
	public static final String version = "0.1.0";

	@Mod.Instance("modtest")
	public static ModTest instance;

	private BlockTest blockTest;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TestGUIHandler());

		blockTest = new BlockTest();
		GameRegistry.register(blockTest);
		GameRegistry.register(new ItemBlock(blockTest).setRegistryName(blockTest.getRegistryName()));

		GameRegistry.registerTileEntity(TileEntityTest.class, "tileEntity");
	}

	public static class TestGUIHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				case 0:
					return new ContainerTest(player, (TileEntityTest) world.getTileEntity(new BlockPos(x, y, z)));
				case 2:
				case 3:
				case 4:
					return new ContainerPlayerInv(new BlockPos(x, y, z), player.inventory);
				default:
					return null;
			}
		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				case 2:
					UIFixedView view = new UIFixedView(176, 166, "root");
					UIFixedView top = new UIFixedView(176, 166 / 2, "top");
					top.setStyle(UIAttribute.VERTICAL_LAYOUT, UIVerticalLayoutMode.TOP);
					UIButtonDyeColor btn = new UIButtonDyeColor(System.out::println, "color");
					top.add(btn);
					view.add(top);

					return new UIContainerWrapper(new ContainerPlayerInv(new BlockPos(x, y, z), player.inventory)).add(view).layout();
				case 3:
					return UIContainerTest.create(player.inventory);
				case 4:
					return UIDSLTestKt.createGui(player);
				default:
					return null;
			}
		}
	}

}
