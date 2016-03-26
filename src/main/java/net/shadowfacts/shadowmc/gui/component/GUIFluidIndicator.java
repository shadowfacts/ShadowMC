package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.util.Color;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * @author shadowfacts
 */
public class GUIFluidIndicator extends GUIComponent {

	private Color borderColor = new Color(0xFF373737);

	private IFluidTank tank;

	public GUIFluidIndicator(int x, int y, int height, IFluidTank tank) {
		super(x, y, 20, height);
		this.tank = tank;
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		drawRect(x, y, width, height, borderColor);

		float level = tank.getFluidAmount() / (float)tank.getCapacity();
		int filled = Math.min((int)(level * height), height);

		bindTexture(TextureMap.locationBlocksTexture);

		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill(tank.getFluid()).toString());

		int x = this.x + 2;
		int y = this.y + (height - filled) - 2;

		float minU = sprite.getInterpolatedU(0);
		float minV = sprite.getInterpolatedV(0);
		float maxU = sprite.getInterpolatedU(16);
		float maxV = sprite.getInterpolatedV(16);

		for (int i = 0; i < filled / 16; i++) {
			drawFluidQuad(x, y + (i * 16), 16, 16, minU, minV, maxU, maxV);
		}
		if (filled % 16 != 0) {
			drawFluidQuad(x, y + filled - (filled % 16), 16, filled % 16, minU, minV, maxU, sprite.getInterpolatedV(filled % 16));
		}

	}

	private void drawFluidQuad(int x, int y, int width, int height, float minU, float minV, float maxU, float maxV) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos((double)x, (double)(y + height), (double)this.zLevel).tex(minU, maxV).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(maxU, maxV).endVertex();
		worldrenderer.pos((double)(x + width), (double)y, (double)this.zLevel).tex(maxU, minV).endVertex();
		worldrenderer.pos((double)x, (double)y, (double)this.zLevel).tex(minU, minV).endVertex();
		tessellator.draw();
	}

	@Override
	public List<String> getTooltip() {
		return ImmutableList.of(String.format("%s %d / %d mB", tank.getFluid().getLocalizedName(), tank.getFluidAmount(), tank.getCapacity()));
	}

}
