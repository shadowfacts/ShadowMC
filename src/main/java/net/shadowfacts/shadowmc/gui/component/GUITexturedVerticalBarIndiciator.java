package net.shadowfacts.shadowmc.gui.component;

import net.shadowfacts.shadowmc.util.Color;

import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class GUITexturedVerticalBarIndiciator extends GUIVerticalBarIndicator {

	public GUITexturedVerticalBarIndiciator(int x, int y, int width, int height, Supplier<Float> levelSupplier) {
		super(x, y, width, height, levelSupplier);
		primaryColor = new Color(0x77FF0000);
		secondaryColor = new Color(0xBB330000);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		float level = levelSupplier.get();
		int filled = Math.min((int)(level * height), height);
		bindTexture(widgetTextures);

		for (int i = 0; i < height / 50; i++) {
			drawTexturedRect(x, y + (i * 50), 0, 20, width, height);
		}
		if (height % 50 != 0) {
			drawTexturedRect(x, y + height - (height % 50), 0, 20, width, height % 50);
		}

		drawRect(x, y + (height - filled), width, filled, primaryColor, zLevel + 0.005f);
		drawRect(x, y, width, height - filled, secondaryColor, zLevel + 0.005f);
	}

}
