package net.shadowfacts.shadowmc.render;

import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class RenderUtils {

	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255f;
		float green = (color >> 8 & 255) / 255f;
		float blue = (color & 255) / 255f;
		GL11.glColor4f(red, green, blue, 1);
	}

}
