package net.shadowfacts.shadowcore.client.gui;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.shadowfacts.shadowcore.ShadowCore;
import net.shadowfacts.shadowcore.config.ConfigManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ShadowCoreConfigGui extends GuiConfig {

	public ShadowCoreConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), ShadowCore.modId, false, false, "ShadowCore Configuration");
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		list.add(categoryElement("misc",  "Miscellaneous", "shadowcore.configgui.ctgy.misc"));
		list.add(categoryElement("worldprovider", "World Provide (DISABLED)", "shadowcore.configgui.ctgy.worldprovider"));

		return list;
	}

	private static IConfigElement categoryElement(String category, String name, String tooltipKey) {
		ConfigElement configElement = new ConfigElement(ConfigManager.instance.getConfigurationObject("Core").getCategory(category));
		return new DummyConfigElement.DummyCategoryElement(name, tooltipKey, configElement.getChildElements());
	}

}
