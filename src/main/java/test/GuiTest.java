package test;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.gui.component.GUIComponentText;
import net.shadowfacts.shadowmc.gui.component.button.GUIComponentButton;
import net.shadowfacts.shadowmc.gui.component.button.GUIComponentButtonText;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public class GuiTest extends BaseGUI {

	protected GUIComponentButtonText button;

	public GuiTest(Minecraft mc) {
		super(mc, 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);

		addChild(new GUIComponentText(mc, 50, 50, "Hello, GUI system!"));
		button = addChild(new GUIComponentButtonText(mc, 50, 66, 100, 20, this::buttonPressed, "Test 1"));
		button.setVisible(false);
	}

	@Override
	public void draw() {
		super.draw();
	}

	protected GUIComponentButton.ButtonPressResult buttonPressed(MouseButton mouseButton) {
		switch (button.getText()) {
			case "Test 1":
				button.setText("Test 2");
				break;
			case "Test 2":
				button.setText("Test 1");
				break;
			default:
				return GUIComponentButton.ButtonPressResult.FAILURE;
		}
		return GUIComponentButton.ButtonPressResult.SUCCESS;
	}
}
