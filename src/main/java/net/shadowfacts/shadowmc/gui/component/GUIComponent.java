package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.gui.AbstractGUI;

/**
 * @author shadowfacts
 */
public class GUIComponent extends AbstractGUI {

	protected static final ResourceLocation widgetTextures = new ResourceLocation("shadowmc", "textures/gui/widgets.png");

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
