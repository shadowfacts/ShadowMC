package net.shadowfacts.shadowmc.ui.element.view;

import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;

/**
 * @author shadowfacts
 */
public class UIFixedView extends UIView {

	public final int width;
	public final int height;

	public UIFixedView(int width, int height, String id, String... classes) {
		super("fixed", id, classes);
		this.width = width;
		this.height = height;
	}

	@Override
	public void layout(int minX, int maxX, int minY, int maxY) {
		super.layout(minX, maxX, minY, maxY);

		for (UIElement e : children) {
			int marginLeft = e.getStyle(UIAttribute.MARGIN_LEFT);
			int marginRight = e.getStyle(UIAttribute.MARGIN_RIGHT);
			int marginTop = e.getStyle(UIAttribute.MARGIN_TOP);
			int marginBottom = e.getStyle(UIAttribute.MARGIN_BOTTOM);
			e.layout(marginLeft, dimensions.width - marginRight, marginTop, dimensions.height - marginBottom);
			e.setX(x + e.getX());
			e.setY(y + e.getY());
		}
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(width, height);
	}

}
