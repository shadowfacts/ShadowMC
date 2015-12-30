package net.shadowfacts.shadowmc.gui;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
@Getter
public abstract class AbstractGUI {

	protected final Minecraft mc;

	private int x;
	private int y;
	private int width;
	private int height;

	@Setter
	private AbstractGUI parent;
	private List<AbstractGUI> children = new ArrayList<>();

	private boolean visible = true;

	private List<String> tooltip = new ArrayList<>();

	public AbstractGUI(Minecraft mc, int x, int y, int width, int height) {
		this.mc = mc;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean hasChildren() {
		return true;
	}

	public <T extends AbstractGUI> T addChild(T child) {
		child.setParent(this);
		children.add(child);
		return child;
	}

	public AbstractGUI getRoot() {
		if (getParent() != null) {
			return getParent().getRoot();
		}
		return this;
	}

	public boolean isWithinBounds(int mouseX, int mouseY) {
		return mouseX >= getX() && mouseX <= getX() + getWidth() &&
				mouseY >= getY() && mouseY <= getY() + getHeight();
	}

	public void drawHoveringText(List<String> text, int x, int y) {
		getRoot().drawHoveringText(text, x, y);
	}

	public void draw() {

	}

	public void drawTooltip(int x, int y) {
		drawHoveringText(getTooltip(), x, y);
	}

	public void update() {

	}

	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {

	}

	public void handleMouseReleased(int mouseX, int mouseY, MouseButton button) {

	}

	public void handleKeyPress(int keyCode, char charTyped) {

	}

}
