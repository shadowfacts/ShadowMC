package net.shadowfacts.shadowmc.gui;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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

	protected float zLevel = 0;

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

	protected void drawCenteredText(String text, int x, int maxX, int y, int maxY, int color) {
		int centerX = x + ((maxX - x) / 2) - (mc.fontRendererObj.getStringWidth(text) / 2);
		int centerY = y + ((maxY - y) / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2);
		drawText(text, centerX, centerY, color);
	}

	protected void drawCenteredText(String text, int x, int maxX, int y, int maxY) {
		drawCenteredText(text, x, maxX, y, maxY, 0xffffff);
	}

	protected void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(u + 0) * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + 0) * f1)).endVertex();
		worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(u + 0) * f), (double)((float)(v + 0) * f1)).endVertex();
		tessellator.draw();
	}

	public void update() {

	}

	public void draw(int mouseX, int mouseY) {

	}

	public void drawTooltip(int x, int y) {
		drawHoveringText(getTooltip(), x, y);
	}

	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {

	}

	public void handleMouseReleased(int mouseX, int mouseY, MouseButton button) {

	}

	public void handleKeyPress(int keyCode, char charTyped) {

	}

}
