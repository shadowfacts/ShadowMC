package net.shadowfacts.shadowmc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class GUIUtils {

	public static void drawText(String text, int x, int y, Color color, boolean shadow) {
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.pushMatrix();
		if (shadow) {
			mc.fontRendererObj.drawStringWithShadow(text, x, y, color.toARGB());
		} else {
			mc.fontRendererObj.drawString(text, x, y, color.toARGB());
		}
		Color.WHITE.apply();
		GL11.glPopMatrix();
	}

	public static void drawText(String text, int x, int y, boolean shadow) {
		drawText(text, x, y, Color.WHITE, shadow);
	}

	public static void drawText(String text, int x, int y, Color color) {
		drawText(text, x, y, color, false);
	}

	public static void drawText(String text, int x, int y) {
		drawText(text, x, y, Color.WHITE);
	}

	public static void drawCenteredText(String text, int x, int maxX, int y, int maxY, Color color) {
		Minecraft mc = Minecraft.getMinecraft();
		int centerX = x + ((maxX - x) / 2) - (mc.fontRendererObj.getStringWidth(text) / 2);
		int centerY = y + ((maxY - y) / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2);
		drawText(text, centerX, centerY, color);
	}

	public static void drawCenteredText(String text, int x, int maxX, int y, int maxY) {
		drawCenteredText(text, x, maxX, y, maxY, Color.WHITE);
	}

	public static void bindTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}

	public static void drawTexturedRect(int x, int y, int u, int v, int width, int height, float zLevel) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos((double)x, (double)(y + height), (double)zLevel).tex((double)((float)u * f), (double)((float)(v + height) * f1)).endVertex();
		buffer.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
		buffer.pos((double)(x + width), (double)y, (double)zLevel).tex((double)((float)(u + width) * f), (double)((float)v * f1)).endVertex();
		buffer.pos((double)x, (double)y, (double)zLevel).tex((double)((float)u * f), (double)((float)v * f1)).endVertex();
		tessellator.draw();
	}

	public static void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
		drawTexturedRect(x, y, u, v, width, height, 0);
	}

	public static void drawRect(int x, int y, int width, int height, Color color, float zLevel) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.enableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		color.apply();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		buffer.pos((double)x, (double)y + height, zLevel).endVertex();
		buffer.pos((double)x + width, (double)y + height, zLevel).endVertex();
		buffer.pos((double)x + width, (double)y, zLevel).endVertex();
		buffer.pos((double)x, (double)y, zLevel).endVertex();
		tessellator.draw();
		Color.WHITE.apply();
		GlStateManager.disableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public static void drawRect(int x, int y, int width, int height, Color color) {
		drawRect(x, y, width, height, color, 0);
	}

}
