package net.shadowfacts.shadowmc.ui.element.view;

import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.style.*;

import java.util.OptionalInt;

/**
 * @author shadowfacts
 */
public class UIStackView extends UIView {

	public UIStackView(String id, String... classes) {
		super("stack", id, classes);
	}

	@Override
	public void layout(int minX, int maxX, int minY, int maxY) {
		super.layout(minX, maxX, minY, maxY);

		UIOrientation stackMode = getStyle(UIAttribute.ORIENTATION);
		int spacing = getStyle(UIAttribute.STACK_SPACING);

		if (stackMode == UIOrientation.VERTICAL) {

			int currentY = 0;
			for (UIElement e : children) {
				UIDimensions elementDimensions = e.getPreferredDimensions();
				e.layout(0, dimensions.width, currentY, currentY + elementDimensions.height);
				e.setX(x + e.getX());
				e.setY(y + currentY);
				currentY += elementDimensions.height + spacing;
			}

		} else {

			int currentX = 0;
			for (UIElement e : children) {
				UIDimensions elementDimensions = e.getPreferredDimensions();
				e.layout(currentX, currentX + elementDimensions.width, 0, dimensions.height);
				e.setX(x + currentX);
				e.setY(y + e.getY());
				currentX += elementDimensions.width + spacing;
			}

		}
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		UIOrientation stackMode = getStyle(UIAttribute.ORIENTATION);
		int spacing = getStyle(UIAttribute.STACK_SPACING);
		if (stackMode == UIOrientation.VERTICAL) {
			OptionalInt width = children.stream()
					.map(UIElement::getPreferredDimensions)
					.mapToInt(dimensions -> dimensions.width)
					.max();
			if (width.isPresent()) {
				int height = children.stream()
						.map(UIElement::getPreferredDimensions)
						.mapToInt(dimensions -> dimensions.height)
						.map(i -> i + spacing)
						.sum() - spacing;
				return new UIDimensions(width.getAsInt(), height);
			} else {
				return new UIDimensions(0, 0);
			}
		} else {
			OptionalInt height = children.stream()
					.map(UIElement::getPreferredDimensions)
					.mapToInt(dimensions -> dimensions.height)
					.max();
			if (height.isPresent()) {
				int width = children.stream()
						.map(UIElement::getPreferredDimensions)
						.mapToInt(dimensions -> dimensions.width)
						.map(i -> i + spacing)
						.sum() - spacing;
				return new UIDimensions(width, height.getAsInt());
			} else {
				return new UIDimensions(0, 0);
			}
		}
	}

}
