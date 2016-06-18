package test;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumFacing;
import net.shadowfacts.shadowmc.gui.component.GUIFluidIndicator;
import net.shadowfacts.shadowmc.gui.component.GUIOxygenIndicator;
import net.shadowfacts.shadowmc.gui.component.GUIVerticalBarIndicator;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonEnum;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonText;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiContainerWrapper;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCBaseGUIContainer;
import net.shadowfacts.shadowmc.oxygen.OxygenCaps;
import net.shadowfacts.shadowmc.util.MouseButton;
import net.shadowfacts.shadowmc.util.RedstoneMode;

/**
 * @author shadowfacts
 */
public class GUITest extends MCBaseGUIContainer {

	public GUITest(GuiContainerWrapper wrapper, TileEntityTest te) {
		super(wrapper);

		addChild(new GUIVerticalBarIndicator(0, 30, 20, 100, this::getValue));
		addChild(new GUIButtonEnum<>(0, 0, 100, 20, RedstoneMode.ALWAYS, RedstoneMode::localize));
		addChild(new GUIFluidIndicator(30, 30, 20, 100, te.tank));
		addChild(new GUIOxygenIndicator(100, 100, 20, 100, te.getCapability(OxygenCaps.HANDLER, EnumFacing.NORTH)));
	}


	@Override
	public void draw(int mouseX, int mouseY) {
		super.draw(mouseX, mouseY);
	}

	private boolean buttonPressed(GUIButtonText button, MouseButton mouseButton) {
		System.out.println("pressed");
		return true;
	}

	private float val;
	private Float getValue() {
		val += 0.01f;
		return val < 1 ? val : (val = 0);
	}

	public static GuiScreen create(EntityPlayer player, TileEntityTest te) {
		GuiContainerWrapper wrapper = new GuiContainerWrapper(new ContainerTest(player, te));
		GUITest gui = new GUITest(wrapper, te);
		gui.setZLevel(100);
		wrapper.gui = gui;
		return wrapper;
	}

}
