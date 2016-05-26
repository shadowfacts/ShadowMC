package net.shadowfacts.shadowmc;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.config.GUIConfig;

/**
 * @author shadowfacts
 */
public class ShadowMCConfigGUI extends GUIConfig {

	public ShadowMCConfigGUI(GuiScreen parent) {
		super(parent, ShadowMC.modId, ShadowMCConfig.config);
	}

}
