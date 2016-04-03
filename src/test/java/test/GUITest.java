package test;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.component.GUIFluidIndicator;
import net.shadowfacts.shadowmc.gui.component.textfield.GUIIntField;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiScreenWrapper;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCBaseGUI;

/**
 * @author shadowfacts
 */
public class GUITest extends MCBaseGUI {

	public GUITest(GuiScreenWrapper wrapper, TileEntityTest te) {
		super(wrapper);

		addChild(new GUIFluidIndicator(0, 0, 100, te.tank));
		addChild(new GUIIntField(0, 110, 150, 20, 150, 0, 200, this::valueChanged));
	}

	private void valueChanged(int i) {
		System.out.println(i);
	}

	public static GuiScreen create(TileEntityTest te) {
		GuiScreenWrapper wrapper = new GuiScreenWrapper();
		GUITest gui = new GUITest(wrapper, te);
		gui.setZLevel(100);
		wrapper.gui = gui;
		return wrapper;
	}

}
