package net.shadowfacts.shadowmc.ui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.UIKeyInteractable;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIContainerWrapper;
import net.shadowfacts.shadowmc.ui.mcwrapper.UIScreenWrapper;
import net.shadowfacts.shadowmc.ui.style.stylesheet.Stylesheet;
import net.shadowfacts.shadowmc.ui.style.stylesheet.StylesheetParser;
import org.lwjgl.input.Keyboard;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author shadowfacts
 */
public class UIBuilder {

	private final Set<UIElement> children = new LinkedHashSet<>();
	private final Set<UIKeyInteractable> keyHandlers = new LinkedHashSet<>();
	private Runnable updateHandler;
	private Runnable closeHandler;
	private Boolean pausesGame;

	public UIBuilder() {
		keyHandlers.add((keyCode, keyChar) -> {
			if (keyCode == Keyboard.KEY_E) Minecraft.getMinecraft().displayGuiScreen(null);
		});
		keyHandlers.add((keyCode, keyChar) -> {
			if (keyCode == Keyboard.KEY_ESCAPE) Minecraft.getMinecraft().displayGuiScreen(null);
		});
	}

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

	public UIBuilder clearKeyHandlers() {
		keyHandlers.clear();
		return this;
	}

	public UIBuilder addKeyHandler(UIKeyInteractable handler) {
		keyHandlers.add(handler);
		return this;
	}

	public UIBuilder setUpdateHandler(Runnable updateHandler) {
		this.updateHandler = updateHandler;
		return this;
	}

	public UIBuilder setCloseHandler(Runnable closeHandler) {
		this.closeHandler = closeHandler;
		return this;
	}

	public UIBuilder setPausesGame(boolean pausesGame) {
		this.pausesGame = pausesGame;
		return this;
	}

	public GuiScreen createScreen() {
		UIScreenWrapper wrapper = new UIScreenWrapper();
		children.forEach(wrapper::add);
		wrapper.layout();
		wrapper.setKeyHandlers(keyHandlers);
		wrapper.setUpdateHandler(updateHandler);
		wrapper.setCloseHandler(closeHandler);
		wrapper.setPausesGame(pausesGame != null ? pausesGame : true);
		return wrapper;
	}

	public GuiContainer createContainer(Container container) {
		UIContainerWrapper wrapper = new UIContainerWrapper(container);
		children.forEach(wrapper::add);
		wrapper.layout();
		wrapper.setKeyHandlers(keyHandlers);
		wrapper.setUpdateHandler(updateHandler);
		wrapper.setCloseHandler(closeHandler);
		wrapper.setPausesGame(pausesGame != null ? pausesGame : false);
		return wrapper;
	}

}
