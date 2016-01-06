package net.shadowfacts.shadowmc.gui.component;

import net.shadowfacts.shadowmc.gui.AbstractGUI;

/**
 * @author shadowfacts
 */
public class GUIComponent extends AbstractGUI {

	public GUIComponent(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	public GUIComponent addTooltip(String tooltip) {
		this.tooltip.add(tooltip);
		return this;
	}
}
