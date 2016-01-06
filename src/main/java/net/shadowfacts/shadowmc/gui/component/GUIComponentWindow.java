package net.shadowfacts.shadowmc.gui.component;

import net.shadowfacts.shadowmc.gui.AbstractGUI;
import net.shadowfacts.shadowmc.gui.BaseGUI;
import net.shadowfacts.shadowmc.util.Color;

/**
 * @author shadowfacts
 */
public class GUIComponentWindow extends BaseGUI {

	protected String title;

	protected Color mainColor = Color.GRAY;
	protected Color titleBarColor = Color.DARK_GRAY;
	protected Color titleColor = Color.WHITE;

	public GUIComponentWindow(int x, int y, int width, int height, String title) {
		super(x, y, width, height);
		this.title = title;
	}

	public GUIComponentWindow addComponent(AbstractGUI component) {
		addChild(component);
		return this;
	}

	@Override
	public boolean isWithinMovableBounds(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width &&
				mouseY >= y && mouseY <= y + 20;
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		drawRect(x, y, width, height, mainColor);
		drawRect(x, y, width, 20, titleBarColor);
		drawCenteredText(title, x + 5, x + 5 + mc.fontRendererObj.getStringWidth(title), y, y + 20, titleColor);
		super.draw(mouseX, mouseY);
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
}
