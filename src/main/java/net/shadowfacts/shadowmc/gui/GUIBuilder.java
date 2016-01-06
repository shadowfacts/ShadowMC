package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiScreenWrapper;
import net.shadowfacts.shadowmc.gui.mcwrapper.MCBaseGUI;

/**
 * @author shadowfacts
 */
public class GUIBuilder {

	private MCBaseGUI gui;

	private GuiScreenWrapper wrapper;

	public GUIBuilder() {
		wrapper = new GuiScreenWrapper();
		gui = new MCBaseGUI(wrapper);
	}

	public GuiScreen wrap() {
		gui.setZLevel(0);
		wrapper.gui = gui;
		return wrapper;
	}

	public GUIBuilder addComponent(AbstractGUI component) {
		gui.addChild(component);
		return this;
	}

	public GUIBuilder setPausesGame(boolean pausesGame) {
		wrapper.setPausesGame(pausesGame);
		return this;
	}

}
