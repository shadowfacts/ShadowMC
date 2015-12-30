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
public abstract class AbstractGUI {

	protected Minecraft mc;

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	@Setter
	protected AbstractGUI parent;
	protected List<AbstractGUI> children = new ArrayList<>();

	@Getter @Setter
	protected boolean visible = true;

	@Getter
	protected List<String> tooltip = new ArrayList<>();

	protected AbstractGUI(Minecraft mc, int x, int y, int width, int height) {
		this.mc = mc;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public AbstractGUI(int x, int y, int width, int height) {
		this(Minecraft.getMinecraft(), x, y, width, height);
	}

	public boolean hasChildren() {
		return true;
	}

	public <T extends AbstractGUI> T addChild(T child) {
		child.parent = this;
		children.add(child);
		return child;
	}

	public AbstractGUI getRoot() {
		if (parent != null) {
			return parent.getRoot();
		}
		return this;
	}

	public boolean isWithinBounds(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width &&
				mouseY >= y && mouseY <= y + height;
	}

	protected void drawHoveringText(List<String> text, int x, int y) {
		getRoot().drawHoveringText(text, x, y);
	}

	protected void drawText(String text, int x, int y, int color) {
		mc.fontRendererObj.drawString(text, x, y, color);
	}

	protected void drawText(String text, int x, int y) {
		drawText(text, x, y, 0xffffff);
	}

	protected void drawCenteredText(String text, int x, int maxX, int y, int color) {
		int center = x + ((maxX - x) / 2) - (mc.fontRendererObj.getStringWidth(text) / 2);
		drawText(text, center, y, color);
	}

	protected void drawCenteredText(String text, int x, int maxX, int y) {
		drawCenteredText(text, x, maxX, y, 0xffffff);
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
