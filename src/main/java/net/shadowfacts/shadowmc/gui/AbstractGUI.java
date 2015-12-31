package net.shadowfacts.shadowmc.gui;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

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

	protected boolean movable = false;

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

	protected void drawText(String text, int x, int y, Color color) {
		mc.fontRendererObj.drawString(text, x, y, color.toARGB());
		GL11.glColor4f(1, 1, 1, 1);
	}

	protected void drawText(String text, int x, int y) {
		drawText(text, x, y, Color.WHITE);
	}

	protected void drawCenteredText(String text, int x, int maxX, int y, int maxY, Color color) {
		int centerX = x + ((maxX - x) / 2) - (mc.fontRendererObj.getStringWidth(text) / 2);
		int centerY = y + ((maxY - y) / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2);
		drawText(text, centerX, centerY, color);
	}

	protected void drawCenteredText(String text, int x, int maxX, int y, int maxY) {
		drawCenteredText(text, x, maxX, y, maxY, Color.WHITE);
	}

	protected void bindTexture(ResourceLocation texture) {
		mc.getTextureManager().bindTexture(texture);
	}

	protected void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos((double)x, (double)(y + height), (double)this.zLevel).tex((double)((float)u * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)y, (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)v * f1)).endVertex();
		worldrenderer.pos((double)x, (double)y, (double)this.zLevel).tex((double)((float)u * f), (double)((float)v * f1)).endVertex();
		tessellator.draw();
	}

	protected void drawRect(int x, int y, int width, int height, Color color) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		color.apply();
		worldRenderer.begin(7, DefaultVertexFormats.POSITION);
		worldRenderer.pos((double)x, (double)y + height, zLevel).endVertex();
		worldRenderer.pos((double)x + width, (double)y + height, zLevel).endVertex();
		worldRenderer.pos((double)x + width, (double)y, zLevel).endVertex();
		worldRenderer.pos((double)x, (double)y, zLevel).endVertex();
		tessellator.draw();
		Color.WHITE.apply();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
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

	public void handleMouseMove(int mouseX, int mouseY, MouseButton mouseButton) {

	}

	public void handleMouseReleased(int mouseX, int mouseY, MouseButton button) {

	}

	public void handleKeyPress(int keyCode, char charTyped) {

	}

	public void updatePosition(int newX, int newY) {
		x = newX;
		y = newY;
	}

}
