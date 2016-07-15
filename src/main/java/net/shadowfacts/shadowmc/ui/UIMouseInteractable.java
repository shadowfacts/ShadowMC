package net.shadowfacts.shadowmc.ui;

import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public interface UIMouseInteractable {

	default void mouseClickDown(int mouseX, int mouseY, MouseButton button) {}

	default void mouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {}

	default void mouseScroll(int mouseX, int mouseY, int delta) {}

	default void mouseMove(int mouseX, int mouseY, MouseButton button, long timeSinceLastClick) {}

}
