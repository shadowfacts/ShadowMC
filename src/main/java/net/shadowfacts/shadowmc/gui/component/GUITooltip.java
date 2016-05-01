package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Supplier;

/**
 * Used for providing tooltip on empty slots
 *
 * @author shadowfacts
 */
public class GUITooltip extends GUIComponent {

	private Supplier<List<String>> tooltipSupplier;
	private Supplier<Boolean> enabledSupplier;

	public GUITooltip(int x, int y, int width, int height, Supplier<List<String>> tooltipSupplier, Supplier<Boolean> enabledSupplier) {
		super(x, y, width, height);
		this.tooltipSupplier = tooltipSupplier;
		this.enabledSupplier = enabledSupplier;
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {

	}

	@Override
	public List<String> getTooltip() {
		return enabledSupplier.get() ? tooltipSupplier.get() : ImmutableList.of();
	}

}
