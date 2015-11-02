package net.shadowfacts.shadowmc.util.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

/**
 * Utility class for rendering {@link net.minecraft.util.AxisAlignedBB}s in world.
 *
 * Usage:
 * Register a new instance of this class with the Forge event bus
 *
 * @author shadowfacts
 */
public class RenderAABB {

	private AxisAlignedBB aabb;
	private Color color;

	private double playerX;
	private double playerY;
	private double playerZ;

	public RenderAABB(AxisAlignedBB aabb, Color color) {
		this.aabb = getAABB(aabb);
		this.color = color;
	}

	@SubscribeEvent
	public void renderWorldLast(RenderWorldLastEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		playerX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.partialTicks;
		playerY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.partialTicks;
		playerZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.partialTicks;


		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(3.0f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

//		Render outline
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		renderOutline();

//		Reset
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void renderOutline() {
		Tessellator t = Tessellator.instance;

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
		t.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
		t.draw();

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
		t.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
		t.draw();

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
		t.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
		t.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
		t.draw();

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		t.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
		t.draw();

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		t.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
		t.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
		t.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		t.draw();

		t.startDrawing(GL11.GL_QUADS);
		t.setColorRGBA_F(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		t.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
		t.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
		t.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
		t.draw();
	}

	private AxisAlignedBB getAABB(AxisAlignedBB aabb) {
		AxisAlignedBB ret = aabb.copy();
		ret.maxX += 1;
		ret.maxY += 1;
		ret.maxZ += 1;
		return ret.expand(0.005f, 0.005f, 0.005f).getOffsetBoundingBox(-playerX, -playerY, -playerZ);
	}

}
