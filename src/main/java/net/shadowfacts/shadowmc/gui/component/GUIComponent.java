package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.gui.AbstractGUI;

/**
 * @author shadowfacts
 */
public class GUIComponent extends AbstractGUI {

	protected GUIComponent(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	public GUIComponent(int x, int y, int width, int height) {
		this(Minecraft.getMinecraft(), x, y, width, height);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
}
