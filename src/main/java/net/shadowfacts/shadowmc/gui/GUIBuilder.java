package net.shadowfacts.shadowmc.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.gui.mcwrapper.GuiScreenWrapper;

/**
 * @author shadowfacts
 */
public class GUIBuilder {

	private Minecraft mc;
	private BaseGUI gui;

	private GuiScreenWrapper wrapper;

	public GUIBuilder(Minecraft mc) {
		this.mc = mc;
		gui = new BaseGUI(mc);
		wrapper = new GuiScreenWrapper(gui);
	}

	public GUIBuilder() {
		this(Minecraft.getMinecraft());
	}

	public GuiScreen wrap() {
		return wrapper;
	}

	public GUIBuilder addComponent(AbstractGUI component) {
		component.mc = mc;
		gui.addChild(component);
		return this;
	}

	public GUIBuilder setPausesGame(boolean pausesGame) {
		wrapper.setPausesGame(pausesGame);
		return this;
	}

}
