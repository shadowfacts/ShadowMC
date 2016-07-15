package test;

import net.minecraft.client.gui.GuiScreen;
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
import net.shadowfacts.shadowmc.gui.GUIBuilder;
import net.shadowfacts.shadowmc.gui.component.GUIComponent;
import net.shadowfacts.shadowmc.gui.component.GUIComponentTexture;
import net.shadowfacts.shadowmc.structure.StructureManager;
import net.shadowfacts.shadowmc.ui.element.UILabel;
import net.shadowfacts.shadowmc.ui.element.button.UIButtonLink;
import net.shadowfacts.shadowmc.ui.element.button.UIButtonRedstoneMode;
import net.shadowfacts.shadowmc.ui.element.button.UIButtonText;
import net.shadowfacts.shadowmc.ui.element.button.UIImage;
import net.shadowfacts.shadowmc.ui.element.view.UIStackView;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIScreenWrapper;
import net.shadowfacts.shadowmc.ui.style.*;

import java.awt.Color;

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
		GameRegistry.register(blockTest);
		GameRegistry.register(new ItemBlock(blockTest).setRegistryName(blockTest.getRegistryName()));

		GameRegistry.registerTileEntity(TileEntityTest.class, "tileEntity");

		StructureManager.INSTANCE.register(new ResourceLocation(modId, "test"));
		StructureManager.INSTANCE.registerReloadHandler(new ResourceLocation(modId, "test"), StructureManager.INSTANCE::load);
	}

	private static GuiScreen create1() {
		return new GUIBuilder()
				.addComponent(new GUIComponentTexture(0, 0, 256, 256, GUIComponent.widgetTextures))
				.wrap();
	}

	public static class TestGUIHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				case 0:
					return new ContainerTest(player, (TileEntityTest) world.getTileEntity(new BlockPos(x, y, z)));
				case 3:
					return new ContainerPlayerInv(player.inventory);
				default:
					return null;
			}
		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			switch (ID) {
				case 0:
					return GUITest.create(player, (TileEntityTest)world.getTileEntity(new BlockPos(x, y, z)));
				case 1:
					return create1();
				case 2:
//					UIStackView stack = new UIStackView();
//					stack.setStyle(UIAttribute.ORIENTATION, UIOrientation.VERTICAL);
//					stack.setStyle(UIAttribute.STACK_SPACING, 5);
//
//					UILabel label1 = new UILabel("Hello, World!");
//					label1.setStyle(UIAttribute.TEXT_COLOR, Color.RED);
//					label1.setStyle(UIAttribute.TEXT_UNDERLINE, true);
//					stack.add(label1);
//
//					UIStackView stack2 = new UIStackView();
//					stack2.setStyle(UIAttribute.ORIENTATION, UIOrientation.HORIZONTAL);
//					stack2.setStyle(UIAttribute.STACK_SPACING, 5);
//
//					UIButtonText button1 = new UIButtonText("Testerino!", (btn, mouseButton) -> {
//						System.out.println("Testerino! pressed");
//						return true;
//					});
//					button1.setStyle(UIAttribute.TEXT_COLOR, Color.GREEN);
//					button1.setStyle(UIAttribute.TEXT_UNDERLINE, true);
//					stack2.add(button1);
//
//					UIButtonLink button2 = new UIButtonLink("I'm a Link!", "https://twitter.com/ShadowfactsDev");
//					stack2.add(button2);
//
//					UIButtonRedstoneMode button3 = new UIButtonRedstoneMode(mode -> {
//						System.out.println("Redstone mode changed to: " + mode.localize());
//					});
//					stack2.add(button3);
//
//					stack.add(stack2);
//
//					UIButtonText button4 = new UIButtonText("I'm disabled!", (btn, mouseButton) -> {
//						System.out.println("Clicked!");
//						return true;
//					});
//					button4.setEnabled(false);
//					stack.add(button4);
//
//					UIButtonToggle button5 = new UIButtonToggle(btn -> {
//						System.out.println("State: " + btn.getState());
//					});
//					stack.add(button5);
//
//					return new UIScreenWrapper().add(stack).layout();
					return null;
				case 3:
					return UIContainerTest.create(player.inventory);
				default:
					return null;
			}
		}
	}

}
