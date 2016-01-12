package net.shadowfacts.shadowmc.gui.handler;

import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public interface ClickHandler {

	void handleMouseClicked(int mouseX, int mouseY, MouseButton button);

	default void handleMouseReleased(int mouseX, int mouseY, MouseButton button) {

	}

	default void handleMouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {

	}

}
