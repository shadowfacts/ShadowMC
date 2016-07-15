package net.shadowfacts.shadowmc.ui.element;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Setter;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.style.UIOrientation;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class UIBarIndicator extends UIElementBase {

	protected final Supplier<Float> levelSupplier;

	@Setter
	protected Consumer<List<String>> tooltip;

	public UIBarIndicator(Supplier<Float> levelSupplier, Consumer<List<String>> tooltip, String id, String... classes) {
		super("bar-indicator", id, classes);
		this.levelSupplier = levelSupplier;
		this.tooltip = tooltip;
	}

	public UIBarIndicator(Supplier<Float> levelSupplier, String id, String... classes) {
		this(levelSupplier, ImmutableList::of, id, classes);
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		UIOrientation orientation = getStyle(UIAttribute.ORIENTATION);
		switch (orientation) {
			default:
			case VERTICAL:
				return new UIDimensions(20, 100);
			case HORIZONTAL:
				return new UIDimensions(100, 20);
		}
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		float level = levelSupplier.get();

		Color primaryColor = getStyle(UIAttribute.PRIMARY_COLOR);
		Color secondaryColor = getStyle(UIAttribute.SECONDARY_COLOR);

		UIOrientation orientation = getStyle(UIAttribute.ORIENTATION);
		if (orientation == UIOrientation.VERTICAL) {
			int filled = Math.min((int)(level * dimensions.height), dimensions.height);
			UIHelper.drawRect(x, y + (dimensions.height - filled), dimensions.width, filled, primaryColor);
			UIHelper.drawRect(x, y, dimensions.width, dimensions.height - filled, secondaryColor);
		} else {
			int filled = Math.min((int)(level * dimensions.width), dimensions.width);
			UIHelper.drawRect(x + dimensions.width - filled, y, filled, dimensions.height, primaryColor);
			UIHelper.drawRect(x, y, dimensions.width - filled, dimensions.height, secondaryColor);
		}
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
		if (UIHelper.isWithinBounds(mouseX, mouseY, x, y, dimensions)) {
			List<String> tooltip = new ArrayList<>();
			this.tooltip.accept(tooltip);
			if (!tooltip.isEmpty()) {
				UIHelper.drawHoveringText(tooltip, mouseX, mouseY);
			}
		}
	}

}
