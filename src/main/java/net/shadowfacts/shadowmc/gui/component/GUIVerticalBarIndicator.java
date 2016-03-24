package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;
import net.shadowfacts.shadowmc.util.Color;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class GUIVerticalBarIndicator extends GUIComponent {

	protected Supplier<Float> levelSupplier;
	private Supplier<List<String>> tooltipSupplier;

	protected Color primaryColor = Color.PURE_RED;
	protected Color secondaryColor = Color.DARK_RED;

	public GUIVerticalBarIndicator(int x, int y, int width, int height, Supplier<Float> levelSupplier, Supplier<List<String>> tooltipSupplier) {
		super(x, y, width, height);
		this.levelSupplier = levelSupplier;
		this.tooltipSupplier = tooltipSupplier;
	}

	public GUIVerticalBarIndicator(int x, int y, int width, int height, Supplier<Float> levelSupplier) {
		this(x, y, width, height, levelSupplier, ImmutableList::of);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		float level = levelSupplier.get();
		int filled = Math.min((int)(level * height), height);
		drawRect(x, y + (height - filled), width, filled, primaryColor);
		drawRect(x, y, width, height - filled, secondaryColor);
	}

	public GUIVerticalBarIndicator setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
		return this;
	}

	public GUIVerticalBarIndicator setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
		return this;
	}

	@Override
	public List<String> getTooltip() {
		return tooltipSupplier.get();
	}

}
