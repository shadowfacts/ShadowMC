package net.shadowfacts.shadowmc.ui.element;

import net.minecraft.client.resources.I18n;
import net.shadowfacts.shadowmc.oxygen.OxygenHandler;

/**
 * @author shadowfacts
 */
public class UIOxygenIndicator extends UIBarIndicator {

	public UIOxygenIndicator(OxygenHandler handler, String id, String... classes) {
		super(() -> {
			return handler.getStored() / handler.getCapacity();
		}, (list) -> {
			list.add(I18n.format("shadowmc.oxygen.level", handler.getStored(), handler.getCapacity()));
		}, id, classes);
	}

}
