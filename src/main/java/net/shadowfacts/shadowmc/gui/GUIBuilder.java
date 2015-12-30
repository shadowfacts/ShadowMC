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

	public GUIBuilder(Minecraft mc) {
		this.mc = mc;
		this.gui = new BaseGUI(mc);
	}

	public GUIBuilder() {
		this(Minecraft.getMinecraft());
	}

	public GuiScreen wrap() {
		return new GuiScreenWrapper(gui);
	}

	public GUIBuilder addComponent(AbstractGUI component) {
		component.mc = mc;
		gui.addChild(component);
		return this;
	}
}
