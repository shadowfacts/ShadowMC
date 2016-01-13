package net.shadowfacts.shadowmc.gui.handler;

/**
 * @author shadowfacts
 */
public interface KeyHandler {

	void handleKeyPress(int keyCode, char charTyped);

	default boolean acceptsKey(int keyCode, char charTyped) {
		return true;
	}

}
