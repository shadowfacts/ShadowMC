package net.shadowfacts.shadowmc.gui.component;

import com.google.common.collect.ImmutableList;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.util.Color;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * @author shadowfacts
 */
public class GUIFluidIndicator extends GUIComponent {

	@Setter
	private Color border = new Color(0xFF373737);

	private IFluidTank tank;

	public GUIFluidIndicator(int x, int y, int width, int height, IFluidTank tank) {
		super(x, y, width, height);
		this.tank = tank;
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		drawRect(x, y, width, height, border);

		if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
			float level = tank.getFluidAmount() / (float)tank.getCapacity();
			int height = this.height - 4;
			int filled = Math.min((int)(level * height), height);

			bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

			TextureAtlasSprite  sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill(tank.getFluid()).toString());

			int x = this.x  + 2;
			int y = this.y + (height - filled) + 2;

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
	}

	private void drawFluidQuad(int x, int y, int width, int height, float minU, float minV, float maxU, float maxV) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos((double)x, (double)(y + height), (double)this.zLevel).tex(minU, maxV).endVertex();
		buffer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(maxU, maxV).endVertex();
		buffer.pos((double)(x + width), (double)y, (double)this.zLevel).tex(maxU, minV).endVertex();
		buffer.pos((double)x, (double)y, (double)this.zLevel).tex(minU, minV).endVertex();
		tessellator.draw();
	}

	@Override
	public List<String> getTooltip() {
		if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
			return ImmutableList.of(String.format("%s %d / %d mB", tank.getFluid().getLocalizedName(), tank.getFluidAmount(), tank.getCapacity()));
		} else {
			return ImmutableList.of(I18n.format("shadowmc.gui.empty"));
		}
	}

}
