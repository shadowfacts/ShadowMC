package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.gui.AbstractGUI;

/**
 * @author shadowfacts
 */
public abstract class GUIComponent extends AbstractGUI {

	public static final ResourceLocation widgetTextures = new ResourceLocation("shadowmc", "textures/gui/widgets.png");

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
