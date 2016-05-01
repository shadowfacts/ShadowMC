package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;
import net.shadowfacts.shadowmc.util.Color;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class GUITexturedVerticalBarIndicator extends GUIVerticalBarIndicator {

	public GUITexturedVerticalBarIndicator(int x, int y, int width, int height, Supplier<Float> levelSupplier, Supplier<List<String>> tooltipSupplier) {
		super(x, y, width, height, levelSupplier, tooltipSupplier);
		primaryColor = new Color(0x77FF0000);
		secondaryColor = new Color(0xBB330000);
	}

	public GUITexturedVerticalBarIndicator(int x, int y, int width, int height, Supplier<Float> levelSupplier) {
		super(x, y, width, height, levelSupplier, ImmutableList::of);
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
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
