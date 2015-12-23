package net.shadowfacts.shadowmc.render;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FluidRenderer {

	public static final FluidRenderer instance = new FluidRenderer();

	private static final int DISPLAY_STAGES = 100;
	private static final Map<Fluid, int[]> flowingRenderCache = new HashMap<>();
	private static final Map<Fluid, int[]> stillRenderCache = new HashMap<>();
	private static final RenderBlockInfo liquidBlock = new RenderBlockInfo();


	public static IIcon getFluidTexture(FluidStack stack, boolean flowing) {
		if (stack == null) {
			return null;
		}
		return getFluidTexture(stack.getFluid(), flowing);
	}

	public static IIcon getFluidTexture(Fluid fluid, boolean flowing) {
		if (fluid == null) {
			return null;
		}
		IIcon icon = flowing ? fluid.getFlowingIcon() : fluid.getStillIcon();
		if (icon == null) {
			icon = ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
		}
		return icon;
	}

	public static int[] getFluidDisplayLists(FluidStack stack, World world, boolean flowing) {
		if (stack == null) {
			return null;
		}

		Fluid fluid = stack.getFluid();
		if (fluid == null) {
			return null;
		}
		Map<Fluid, int[]> cache = flowing ? flowingRenderCache : stillRenderCache;

		int[] displayLists = cache.get(fluid);
		if (displayLists != null) {
			return displayLists;
		}

		displayLists = new int[DISPLAY_STAGES];

		liquidBlock.baseBlock = fluid.getBlock() != null ? fluid.getBlock() : Blocks.water;
		liquidBlock.texture = getFluidTexture(stack, flowing);

		cache.put(fluid, displayLists);

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);

		for (int i = 0; i < DISPLAY_STAGES; ++i) {
			displayLists[i] = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(displayLists[i], GL11.GL_COMPILE);

			liquidBlock.minX = 0.01f;
			liquidBlock.minY = 0;
			liquidBlock.minZ = 0.01f;

			liquidBlock.maxX = 0.99f;
			liquidBlock.maxY = Math.max(i, 1) / (float)DISPLAY_STAGES;
			liquidBlock.maxZ = 0.99f;

			Renderer.instance.renderBlock(liquidBlock);

			GL11.glEndList();
		}

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);

		return displayLists;
	}


}
