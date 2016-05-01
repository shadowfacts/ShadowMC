package net.shadowfacts.shadowmc.gui.component.window;

import net.shadowfacts.shadowmc.gui.AbstractGUI;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.gui.component.button.GUIButton;
import net.shadowfacts.shadowmc.gui.handler.ClickHandler;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public class GUIComponentWindow extends BaseGUI {

	protected String title;

	protected Color mainColor = Color.GRAY;
	protected Color titleBarColor = Color.DARK_GRAY;
	protected Color titleColor = Color.WHITE;

	protected boolean closable;
	protected boolean minimizable;

	protected boolean minimized = false;

	public GUIComponentWindow(int x, int y, int width, int height, String title, boolean closable, boolean minimizable) {
		super(x, y, width, height);
		this.title = title;
		this.closable = closable;
		this.minimizable = minimizable;
		int leftX = x + width - 10;
		if (closable) {
			addChild(new GUIButtonCloseWindow(leftX, y + 5, 10, 10, this));
			leftX -= 15;
		}
		if (minimizable) {
			addChild(new GUIButtonMinimizeWindow(leftX, y + 5, 10, 10, this));
		}
	}

	public GUIComponentWindow(int x, int y, int width, int height, String title, boolean closable) {
		this(x, y, width, height, title, closable, true);
	}

	public GUIComponentWindow(int x, int y, int width, int height, String title) {
		this(x, y, width, height, title, false, true);
	}

	public GUIComponentWindow addComponent(AbstractGUI component) {
		addChild(component);
		return this;
	}

	@Override
	public boolean isWithinMovableBounds(int x, int y) {
		return x >= this.x && x <= this.x + width &&
				y >= this.y && y <= this.y + 20;
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		if (minimized) {
			Optional<GUIButton> gui = children.stream()
					.filter(theGui -> theGui instanceof GUIButtonCloseWindow || theGui instanceof GUIButtonMinimizeWindow)
					.filter(AbstractGUI::isVisible)
					.filter(theGui -> theGui.isWithinBounds(mouseX, mouseY))
					.sorted((gui1, gui2) -> gui1.getZLevel() > gui2.getZLevel() ? -1 : gui1.getZLevel() < gui2.getZLevel() ? 1 : 0)
					.map(theGui -> (GUIButton)theGui)
					.findFirst();
			if (gui.isPresent()) {
				gui.get().handleMouseClicked(mouseX, mouseY, button);
			}
		} else {
			super.handleMouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		if (!minimized) drawRect(x, y + 20, width, height - 20, mainColor);

		drawRect(x, y, width, 20, titleBarColor);
		drawCenteredText(title, x + 5, x + 5 + mc.fontRendererObj.getStringWidth(title), y, y + 20, titleColor);

		if (minimized) {
			children.stream()
					.filter(gui -> gui instanceof GUIButtonCloseWindow || gui instanceof GUIButtonMinimizeWindow)
					.filter(AbstractGUI::isVisible)
					.sorted((gui1, gui2) -> gui1.getZLevel() > gui2.getZLevel() ? -1 : gui1.getZLevel() < gui2.getZLevel() ? 1 : 0)
					.forEach(gui -> gui.draw(mouseX, mouseY, partialTicks));
		} else {
			super.draw(mouseX, mouseY, partialTicks);
		}
	}

	public void close() {
		if (closable) {
			parent.removeChild(this);
		}
	}

	public void toggleMinimized() {
		if (minimizable) {
			minimized = !minimized;
		}
	}

	public GUIComponentWindow setTitle(String title) {
		this.title = title;
		return this;
	}

	public GUIComponentWindow setMainColor(Color mainColor) {
		this.mainColor = mainColor;
		return this;
	}

	public GUIComponentWindow setTitleBarColor(Color titleBarColor) {
		this.titleBarColor = titleBarColor;
		return this;
	}

	public GUIComponentWindow setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
		return this;
	}

	@Override
	public void setZLevel(float zLevel) {
		this.zLevel = zLevel;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).setZLevel(zLevel + i * 0.001f);
		}
	}

}
