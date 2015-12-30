package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public abstract class AbstractInteractiveGUI extends AbstractGUI {

	public AbstractInteractiveGUI(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	public abstract void handleMouseClicked(int mouseX, int mouseY, MouseButton button);

	public abstract void handleMouseReleased(int mouseX, int mouseY, MouseButton button);

	public abstract void handleKeyPress(int keyCode, char charTyped);

}
