package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.AbstractGUI;

/**
 * @author shadowfacts
 */
public class GUIComponent extends AbstractGUI {

	public GUIComponent(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
}
