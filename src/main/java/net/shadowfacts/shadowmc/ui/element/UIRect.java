package net.shadowfacts.shadowmc.ui.element;

import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

/**
 * @author shadowfacts
 */
public class UIRect extends UIElementBase {

	private int width;
	private int height;

	public UIRect(int width, int height, String id, String... classes) {
		super("rect", id, classes);
		this.width = width;
		this.height = height;
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(width, height);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		UIHelper.drawRect(x, y, dimensions.width, dimensions.height, getStyle(UIAttribute.PRIMARY_COLOR), 500);
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
	}

}
