package net.shadowfacts.shadowmc.ui.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIContainerWrapper;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIScreenWrapper;
import net.shadowfacts.shadowmc.ui.style.stylesheet.Stylesheet;
import net.shadowfacts.shadowmc.ui.style.stylesheet.StylesheetParser;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author shadowfacts
 */
public class UIBuilder {

	private final Set<UIElement> children = new LinkedHashSet<>();

	public UIBuilder add(UIElement e) {
		children.add(e);
		return this;
	}

	public UIBuilder style(Stylesheet stylesheet) {
		children.forEach(stylesheet::style);
		return this;
	}

	public UIBuilder style(String id) {
		return style(StylesheetParser.parse(StylesheetParser.load(id)));
	}

	public GuiScreen createScreen() {
		UIScreenWrapper wrapper = new UIScreenWrapper();
		children.forEach(wrapper::add);
		wrapper.layout();
		return wrapper;
	}

	public GuiContainer createContainer(Container container) {
		UIContainerWrapper wrapper = new UIContainerWrapper(container);
		children.forEach(wrapper::add);
		wrapper.layout();
		return wrapper;
	}

}
