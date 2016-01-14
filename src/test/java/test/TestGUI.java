package test;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.component.GUIComponentText;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiScreenWrapper;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCBaseGUI;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.StringHelper;

/**
 * @author shadowfacts
 */
public class TestGUI extends MCBaseGUI {

//	private NetworkController controller;

	private GUIComponentText status;

	public TestGUI(GuiScreenWrapper mcGUI) {
		super(mcGUI);

		status = addChild(new GUIComponentText(100, 100, "")
				.setColor(Color.GREEN));
	}

	@Override
	public void update() {
		super.update();

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		status.updatePosition(x, y);
		status.setText(StringHelper.localize("gideon.gui.status") + " " + "asdf");
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		if (initialized) drawRect(x, y, 200, 100, Color.BLACK);
		super.draw(mouseX, mouseY);
	}

	public static GuiScreen create() {
		GuiScreenWrapper wrapper = new GuiScreenWrapper();
		TestGUI gui = new TestGUI(wrapper);
		gui.setZLevel(0);
		wrapper.gui = gui;
		return wrapper;
	}

}
