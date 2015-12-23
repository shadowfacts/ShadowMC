package net.shadowfacts.shadowmc.render;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Renderer {

	public static final Renderer instance = new Renderer();

	private RenderBlocks renderBlocks = new RenderBlocks();

	public void renderBlock(RenderBlockInfo info) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		renderBlocks.setRenderBounds(info.minX, info.minY, info.minZ, info.maxX, info.maxY, info.maxZ);

		if (info.light != -1) {
			tessellator.setBrightness(info.light << 20 | info.light << 4);
		} else if (info.brightness != -1) {
			tessellator.setBrightness(info.brightness << 4);
		}

		if (info.renderSide[0]) {
			tessellator.setNormal(0, -1, 0);
			renderBlocks.renderFaceYNeg(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(0));
		}
		if (info.renderSide[1]) {
			tessellator.setNormal(0, 1, 0);
			renderBlocks.renderFaceYPos(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(1));
		}
		if (info.renderSide[2]) {
			tessellator.setNormal(0, 0, -1);
			renderBlocks.renderFaceZNeg(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(2));
		}
		if (info.renderSide[3]) {
			tessellator.setNormal(0, 0, 1);
			renderBlocks.renderFaceZPos(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(3));
		}
		if (info.renderSide[4]) {
			tessellator.setNormal(-1, 0, 0);
			renderBlocks.renderFaceXNeg(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(4));
		}
		if (info.renderSide[5]) {
			tessellator.setNormal(1, 0, 0);
			renderBlocks.renderFaceXPos(info.baseBlock, 0, 0, 0, info.getBlockTextureFromSide(5));
		}

		tessellator.draw();

	}

}
