package net.shadowfacts.shadowmc.gui.component;

import net.shadowfacts.shadowmc.util.Color;

import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class GUIVerticalBarIndicator extends GUIComponent {

//	protected float level; // percent filled

	protected Supplier<Float> levelSupplier;

	protected Color primaryColor = Color.PURE_RED;
	protected Color secondaryColor = Color.DARK_RED;

	public GUIVerticalBarIndicator(int x, int y, int width, int height, Supplier<Float> levelSupplier) {
		super(x, y, width, height);
		this.levelSupplier = levelSupplier;
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

}
