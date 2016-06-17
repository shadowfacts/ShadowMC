package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.resources.I18n;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;

/**
 * @author shadowfacts
 */
public class GUIOxygenIndicator extends GUIVerticalBarIndicator {

	public GUIOxygenIndicator(int x, int y, int width, int height, OxygenHandler handler) {
		super(x, y, width, height, () -> {
			return handler.getStored() / (float)handler.getCapacity();
		}, () -> {
			return ImmutableList.of(I18n.format("shadowmc.oxygen.level", handler.getStored(), handler.getCapacity()));
		});
	}

}
