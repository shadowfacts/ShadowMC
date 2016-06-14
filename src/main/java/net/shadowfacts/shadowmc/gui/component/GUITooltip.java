package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class GUITooltip extends GUIComponent {

	private Supplier<List<String>> tooltip;
	private BooleanSupplier enabled;

	public GUITooltip(int x, int y, int width, int height, Supplier<List<String>> tooltip, BooleanSupplier enabled) {
		super(x, y, width, height);
		this.tooltip = tooltip;
		this.enabled = enabled;
	}

	@Override
	public void draw(int mouseX, int mouseY) {

	}

	@Override
	public List<String> getTooltip() {
		return enabled.getAsBoolean() ? tooltip.get() : ImmutableList.of();
	}

}
