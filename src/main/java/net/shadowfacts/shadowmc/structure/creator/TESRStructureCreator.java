package net.shadowfacts.shadowmc.structure.creator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * @author shadowfacts
 */
public class TESRStructureCreator extends TileEntitySpecialRenderer<TileEntityStructureCreator> {

	@Override
	public void renderTileEntityAt(TileEntityStructureCreator te, double x, double y, double z, float partialTicks, int destroyStage) {
		AxisAlignedBB box = te.getBox();

		Tessellator tessellator = Tessellator.getInstance();

		EntityPlayer player = Minecraft.getMinecraft().player;
		double playerX = player.prevPosX + (player.posX - player.prevPosX) * partialTicks;
		double playerY = player.prevPosY + (player.posY - player.prevPosY) * partialTicks;
		double playerZ = player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks;

		GlStateManager.pushMatrix();
		GlStateManager.translate(-playerX, -playerY, -playerZ);

		VertexBuffer buffer = tessellator.getBuffer();
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();

		GlStateManager.translate((float)box.minX, (float)box.minY, (float)box.minZ);

		int xPos = (int)(box.maxX - box.minX);
		int yPos = (int)(box.maxY - box.minY);
		int zPos = (int)(box.maxZ - box.minZ);

		int red = 0;
		int green = 0;
		int blue = 0;
		int alpha = 255;

//		x edges
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= xPos; i++) {
			buffer.pos(i, 0.001f, 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= xPos; i++) {
			buffer.pos(i, yPos - 0.001f, 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= xPos; i++) {
			buffer.pos(i, yPos - 0.001f, zPos - 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= xPos; i++) {
			buffer.pos(i, 0.001f, zPos - 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();

//		y edges
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= yPos; i++) {
			buffer.pos(0.001f, i,  0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= yPos; i++) {
			buffer.pos(xPos - 0.001f, i, 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= yPos; i++) {
			buffer.pos(xPos - 0.001f, i, zPos - 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= yPos; i++) {
			buffer.pos(0.001f, i, zPos - 0.001f).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();

//		z edges
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= zPos; i++) {
			buffer.pos(0.001f, 0.001f, i).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= zPos; i++) {
			buffer.pos(xPos - 0.001f, 0.001f, i).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= zPos; i++) {
			buffer.pos(xPos - 0.001f, yPos - 0.001f, i).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= zPos; i++) {
			buffer.pos(0.001f, yPos - 0.001f, i).color(red, green, blue, alpha).endVertex();
		}
		tessellator.draw();

		GlStateManager.translate(-(float)box.minX, -(float)box.minY, -(float)box.minZ);
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();

	}

}
