package test;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.gui.component.GUIComponentText;
import net.shadowfacts.shadowmc.gui.component.button.GUIButtonText;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public class GuiTest extends BaseGUI {

	public GuiTest(Minecraft mc) {
		super(mc, 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);

		addChild(new GUIComponentText(mc, 50, 50, "Hello, GUI system!"));
		addChild(new GUIButtonText(mc, 50, 66, 100, 20, this::buttonPressed, "Test 1"));
	}

	@Override
	public void draw() {
		super.draw();
	}

	protected boolean buttonPressed(GUIButtonText button, MouseButton mouseButton) {
		switch (button.getText()) {
			case "Test 1":
				button.setText("Test 2");
				break;
			case "Test 2":
				button.setText("Test 1");
				break;
			default:
				return false;
		}
		return true;
	}
}
