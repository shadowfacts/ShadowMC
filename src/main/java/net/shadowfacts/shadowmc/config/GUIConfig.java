package net.shadowfacts.shadowmc.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GUIConfig extends GuiConfig {

	public GUIConfig(GuiScreen parent, String owner, Configuration config) {
		super(parent, getConfigElements(owner, config), owner, false, false, I18n.format(owner + ".config.gui.title"));
	}

	private static List<IConfigElement> getConfigElements(String owner, Configuration config) {
		List<IConfigElement> list = new ArrayList<>();

		config.getCategoryNames().stream()
				.map(s -> getCategory(owner, config, s))
				.forEach(list::add);

		return list;
	}

	private static IConfigElement getCategory(String owner, Configuration config, String categoryName) {
		ConfigCategory category = config.getCategory(categoryName);
		String name = I18n.format(owner + ".config.gui.category." + categoryName);
		String tooltipKey = owner + ".config.gui.category." + categoryName;

		IConfigElement element = new ConfigElement(category);
		return new DummyConfigElement.DummyCategoryElement(name, tooltipKey, element.getChildElements());
	}

}
