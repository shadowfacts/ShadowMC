package test;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.BasicGUI;
import net.shadowfacts.shadowmc.gui.GUIComponentText;

/**
 * @author shadowfacts
 */
public class GuiTest extends BasicGUI {

	public GuiTest(Minecraft mc) {
		super(mc, 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);

		addChild(new GUIComponentText(mc, 50, 50, "Hello, GUI system!"));
	}

	@Override
	public void draw() {
		super.draw();
	}
}
