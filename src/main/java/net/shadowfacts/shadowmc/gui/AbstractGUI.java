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

	@Getter @Setter
	protected int x;
	@Getter @Setter
	protected int y;
	@Getter @Setter
	protected int width;
	@Getter @Setter
	protected int height;

	@Getter @Setter
	protected float zLevel = 0;

	@Setter
	protected AbstractGUI parent;
	protected List<AbstractGUI> children = new ArrayList<>();

	@Getter @Setter
	protected boolean visible = true;

	@Getter
	protected List<String> tooltip = new ArrayList<>();

	@Getter @Setter
	protected boolean initialized;

	public AbstractGUI(int x, int y, int width, int height) {
		this.mc = Minecraft.getMinecraft();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		initialized = true;
	}

	public boolean hasChildren() {
		return true;
	}

	public <T extends AbstractGUI> T addChild(T child) {
		child.parent = this;
		children.add(child);
		return child;
	}

	public void removeChild(AbstractGUI child) {
		children.remove(child);
	}

	public AbstractGUI getRoot() {
		if (parent != null) {
			return parent.getRoot();
		}
		return this;
	}

	public boolean isWithinBounds(int x, int y) {
		return x >= this.x && x <= this.x + width &&
				y >= this.y && y <= this.y + height;
	}

	public boolean isWithinMovableBounds(int x, int y) {
		return false;
	}

	protected void drawHoveringText(List<String> text, int x, int y) {
		getRoot().drawHoveringText(text, x, y);
	}

	protected void drawText(String text, int x, int y, Color color) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, zLevel + .5f);
		mc.fontRendererObj.drawString(text, x, y, color.toARGB());
		Color.WHITE.apply();
		GL11.glPopMatrix();
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
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos((double)x, (double)(y + height), (double)this.zLevel).tex((double)((float)u * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
		worldrenderer.pos((double)(x + width), (double)y, (double)this.zLevel).tex((double)((float)(u + width) * f), (double)((float)v * f1)).endVertex();
		worldrenderer.pos((double)x, (double)y, (double)this.zLevel).tex((double)((float)u * f), (double)((float)v * f1)).endVertex();
		tessellator.draw();
	}

	protected void drawRect(int x, int y, int width, int height, Color color, float zLevel) {
//		TODO: Fix this, GUI components don't show up behind transparent rects
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.enableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		color.apply();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos((double)x, (double)y + height, zLevel).endVertex();
		worldRenderer.pos((double)x + width, (double)y + height, zLevel).endVertex();
		worldRenderer.pos((double)x + width, (double)y, zLevel).endVertex();
		worldRenderer.pos((double)x, (double)y, zLevel).endVertex();
		tessellator.draw();
		Color.WHITE.apply();
		GlStateManager.disableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	protected void drawRect(int x, int y, int width, int height, Color color) {
		drawRect(x, y, width, height, color, zLevel);
	}

	/**
	 * BROKEN AF
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param angle
	 * @param color
	 */
	@Deprecated
	protected void drawLine(int x, int y, int width, int height, float angle, Color color) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(angle, 1, 1, 1);
		drawRect(x, y, width, height, color);
		GlStateManager.popMatrix();
	}

	@Deprecated
	protected void drawVerticalLine(int x, int y, int width, int height, Color color) {
		drawLine(x, y, width, height, 45, color);
	}

	@Deprecated
	protected void drawHorizontalLine(int x, int y, int width, int height, Color color) {
		drawLine(x, y, width, height, 0, color);
	}

	protected void drawGradientRect(int x, int y, int width, int height, Color topColor, Color bottomColor) {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		worldrenderer.pos((double) x + width, (double) y, (double)this.zLevel).color(topColor.getRed(), topColor.getGreen(), topColor.getBlue(), topColor.getAlpha()).endVertex();
		worldrenderer.pos((double) x, (double) y, (double)this.zLevel).color(topColor.getRed(), topColor.getGreen(), topColor.getBlue(), topColor.getAlpha()).endVertex();
		worldrenderer.pos((double) x, (double) y + height, (double)this.zLevel).color(bottomColor.getRed(), bottomColor.getRed(), bottomColor.getBlue(), bottomColor.getAlpha()).endVertex();
		worldrenderer.pos((double) x + width, (double) y + height, (double)this.zLevel).color(bottomColor.getRed(), bottomColor.getRed(), bottomColor.getBlue(), bottomColor.getAlpha()).endVertex();
		tessellator.draw();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}

	public void update() {

	}

	public abstract void draw(int mouseX, int mouseY);

	public void drawTooltip(int x, int y) {
		drawHoveringText(getTooltip(), x, y);
	}

	public void updatePosition(int newX, int newY) {
		x = newX;
		y = newY;
	}

}
