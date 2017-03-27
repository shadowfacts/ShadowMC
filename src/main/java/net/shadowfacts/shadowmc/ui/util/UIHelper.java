package net.shadowfacts.shadowmc.ui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class UIHelper {

	public static UIDimensions max(UIDimensions a, UIDimensions b) {
		return new UIDimensions(Math.max(a.width, b.width), Math.max(a.height, b.height));
	}

	public static UIDimensions min(UIDimensions a, UIDimensions b) {
		return new UIDimensions(Math.min(a.width, b.width), Math.min(a.height, b.height));
	}

	public static boolean isWithinBounds(int x, int y, int minX, int minY, int maxX, int maxY) {
		return x >= minX && x <= maxX && y >= minY && y <= maxY;
	}

	public static boolean isWithinBounds(int mouseX, int mouseY, int x, int y, UIDimensions dimensions) {
		return isWithinBounds(mouseX, mouseY, x, y, x + dimensions.width, y + dimensions.height);
	}

	public static boolean isWithinBounds(int x, int y, UIElement element) {
		return isWithinBounds(x, y, element.getX(), element.getY(), element.getDimensions());
	}

	public static int toARGB(Color color) {
		return ((color.getAlpha() & 255) << 24) | ((color.getRed() & 255) << 16) | ((color.getGreen() & 255) << 8) | (color.getBlue() & 255);
	}

	public static String styleText(String text, UIElement element) {
		if (element.getStyle(UIAttribute.TEXT_UNDERLINE)) {
			text = TextFormatting.UNDERLINE + text;
		}
		if (element.getStyle(UIAttribute.TEXT_ITALIC)) {
			text = TextFormatting.ITALIC + text;
		}
		if (element.getStyle(UIAttribute.TEXT_BOLD)) {
			text = TextFormatting.BOLD + text;
		}
		return text;
	}

	public static void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos((double)x, (double)(y + height), 0).tex((double)((float)u * f), (double)((float)(v + height) * f1)).endVertex();
		buffer.pos((double)(x + width), (double)(y + height), 0).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
		buffer.pos((double)(x + width), (double)y, 0).tex((double)((float)(u + width) * f), (double)((float)v * f1)).endVertex();
		buffer.pos((double)x, (double)y, 0).tex((double)((float)u * f), (double)((float)v * f1)).endVertex();
		tessellator.draw();
	}

	public static void bindTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}

	public static void drawRect(int x, int y, int width, int height, Color color, float zLevel) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.enableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos((double)x, (double)y + height, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
		buffer.pos((double)x + width, (double)y + height, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
		buffer.pos((double)x + width, (double)y, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
		buffer.pos((double)x, (double)y, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public static void drawRect(int x, int y, int width, int height, Color color) {
		drawRect(x, y, width, height, color, 0);
	}

	public static void drawCenteredText(String text, int minX, int minY, int maxX, int maxY, Color color, boolean shadow) {
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		int x = minX + ((maxX - minX) / 2) - (fontRenderer.getStringWidth(text) / 2);
		int y = minY + ((maxY - minY) / 2) - (fontRenderer.FONT_HEIGHT / 2);
		GlStateManager.pushMatrix();
		if (shadow) {
			fontRenderer.drawStringWithShadow(text, x, y, UIHelper.toARGB(color));
		} else {
			fontRenderer.drawString(text, x, y, UIHelper.toARGB(color));
		}
		GlStateManager.color(1, 1, 1);
		GlStateManager.popMatrix();
	}

	public static void drawHoveringText(List<String> lines, int x, int y) {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		int maxTextWidth = screenWidth - x;
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;

		if (!lines.isEmpty()) {
			GlStateManager.disableRescaleNormal();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			int tooltipTextWidth = 0;

			for (String textLine : lines) {
				int textLineWidth = font.getStringWidth(textLine);

				if (textLineWidth > tooltipTextWidth) {
					tooltipTextWidth = textLineWidth;
				}
			}

			boolean needsWrap = false;

			int titleLinesCount = 1;
			int tooltipX = x + 12;
			if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
				tooltipX = x - 16 - tooltipTextWidth;
				if (tooltipX < 4) { // if the tooltip doesn't fit on the screen
					if (x > screenWidth / 2) {
						tooltipTextWidth = x - 12 - 8;
					} else {
						tooltipTextWidth = screenWidth - 16 - x;
					}
					needsWrap = true;
				}
			}

			if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth) {
				tooltipTextWidth = maxTextWidth;
				needsWrap = true;
			}

			if (needsWrap) {
				int wrappedTooltipWidth = 0;
				List<String> wrappedTextLines = new ArrayList<String>();
				for (int i = 0; i < lines.size(); i++) {
					String textLine = lines.get(i);
					List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
					if (i == 0) {
						titleLinesCount = wrappedLine.size();
					}

					for (String line : wrappedLine) {
						int lineWidth = font.getStringWidth(line);
						if (lineWidth > wrappedTooltipWidth) {
							wrappedTooltipWidth = lineWidth;
						}
						wrappedTextLines.add(line);
					}
				}
				tooltipTextWidth = wrappedTooltipWidth;
				lines = wrappedTextLines;

				if (x > screenWidth / 2) {
					tooltipX = x - 16 - tooltipTextWidth;
				} else {
					tooltipX = x + 12;
				}
			}

			int tooltipY = y - 12;
			int tooltipHeight = 8;

			if (lines.size() > 1) {
				tooltipHeight += (lines.size() - 1) * 10;
				if (lines.size() > titleLinesCount) {
					tooltipHeight += 2; // gap between title lines and next lines
				}
			}

			if (tooltipY + tooltipHeight + 6 > screenHeight) {
				tooltipY = screenHeight - tooltipHeight - 6;
			}

			final int zLevel = 300;
			final int backgroundColor = 0xF0100010;
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			final int borderColorStart = 0x505000FF;
			final int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

			for (int lineNumber = 0; lineNumber < lines.size(); ++lineNumber) {
				String line = lines.get(lineNumber);
				font.drawStringWithShadow(line, (float)tooltipX, (float)tooltipY, -1);

				if (lineNumber + 1 == titleLinesCount) {
					tooltipY += 2;
				}

				tooltipY += 10;
			}

			GlStateManager.enableLighting();
			GlStateManager.enableDepth();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableRescaleNormal();
		}
	}

	public static void drawGradientRect(int zLevel, int left, int top, int right, int bottom, int startColor, int endColor) {
		float startAlpha = (float)(startColor >> 24 & 255) / 255.0F;
		float startRed = (float)(startColor >> 16 & 255) / 255.0F;
		float startGreen = (float)(startColor >> 8 & 255) / 255.0F;
		float startBlue = (float)(startColor & 255) / 255.0F;
		float endAlpha = (float)(endColor >> 24 & 255) / 255.0F;
		float endRed = (float)(endColor >> 16 & 255) / 255.0F;
		float endGreen = (float)(endColor >> 8 & 255) / 255.0F;
		float endBlue = (float)(endColor & 255) / 255.0F;

		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.shadeModel(7425);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		vertexbuffer.pos(right, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		vertexbuffer.pos(left, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		vertexbuffer.pos(left, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		vertexbuffer.pos(right, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		tessellator.draw();

		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}

	public static void drawFluidQuad(int x, int y, int width, int height, float minU, float minV, float maxU, float maxV) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos((double)x, (double)(y + height), 0).tex(minU, maxV).endVertex();
		buffer.pos((double)(x + width), (double)(y + height), 0).tex(maxU, maxV).endVertex();
		buffer.pos((double)(x + width), (double)y, 0).tex(maxU, minV).endVertex();
		buffer.pos((double)x, (double)y, 0).tex(minU, minV).endVertex();
		tessellator.draw();
	}

}
