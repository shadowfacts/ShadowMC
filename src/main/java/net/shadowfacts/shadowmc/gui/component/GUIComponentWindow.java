package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.util.Color;

/**
 * @author shadowfacts
 */
public class GUIComponentWindow extends BaseGUI {

	protected GUIComponentWindow(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
		movable = true;
		addChild(new GUIComponentText(x + 10, y + 10, "Window"));
	}

	public GUIComponentWindow(int x, int y, int width, int height) {
		this(Minecraft.getMinecraft(), x, y, width, height);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		drawRect(x, y, width, height, Color.DARK_GRAY);
		super.draw(mouseX, mouseY);
	}

}
