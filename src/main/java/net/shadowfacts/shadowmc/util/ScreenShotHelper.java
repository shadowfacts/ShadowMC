package net.shadowfacts.shadowmc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.event.EventManager;
import net.shadowfacts.shadowmc.event.ScreenShotEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shadowfacts
 */
public class ScreenShotHelper {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

	private static IntBuffer buffer;
	private static int[] values;

	public static IChatComponent saveScreenshot(File gameDir, String fileName, int width, int height, Framebuffer framebuffer) {
		File screenshotsDir = new File(gameDir, "screenshots");
		if (!screenshotsDir.exists()) screenshotsDir.mkdirs();

		if (OpenGlHelper.isFramebufferEnabled()) {
			width = framebuffer.framebufferTextureWidth;
			height = framebuffer.framebufferTextureHeight;
		}

		int total = width * height;

		if (buffer == null || buffer.capacity() < total) {
			buffer = BufferUtils.createIntBuffer(total);
			values = new int[total];
		}

		GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		buffer.clear();

		if (OpenGlHelper.isFramebufferEnabled()) {
			GlStateManager.bindTexture(framebuffer.framebufferTexture);
			GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buffer);
		} else {
			GL11.glReadPixels(0, 0, width, height, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buffer);
		}

		buffer.get(values);
		TextureUtil.processPixelValues(values, width, height);
		BufferedImage image = null;

		if (OpenGlHelper.isFramebufferEnabled()) {
			image = new BufferedImage(framebuffer.framebufferWidth, framebuffer.framebufferHeight, 1);
			int j = framebuffer.framebufferTextureHeight - framebuffer.framebufferHeight;

			for (int k = j; k < framebuffer.framebufferTextureHeight; ++k) {
				for (int l = 0; l < framebuffer.framebufferWidth; ++l) {
					image.setRGB(l, k - j, values[k * framebuffer.framebufferTextureWidth + l]);
				}
			}
		} else {
			image = new BufferedImage(width, height, 1);
			image.setRGB(0, 0, width, height, values, 0, width);
		}

		File screenshot;

		if (fileName == null) {
			screenshot = getTimestampedPNGFileForDirectory(screenshotsDir);
		} else {
			screenshot = new File(screenshotsDir, fileName);
		}

		ScreenShotEvent.Pre preEvent = EventManager.onSaveScreenshotPre(screenshot);

		if (!preEvent.isCanceled()) {
			screenshot = preEvent.getScreenshotFile();

			try {
				ImageIO.write(image, "png", screenshot);

				ScreenShotEvent.Post postEvent = EventManager.onSaveScreenshotPost(screenshot);

				postEvent.getExtraComponents().stream()
						.forEach(Minecraft.getMinecraft().ingameGUI.getChatGUI()::printChatMessage);

				ChatComponentText file = new ChatComponentText(screenshot.getName());
				file.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, screenshot.getAbsolutePath()));
				file.getChatStyle().setUnderlined(true);
				return new ChatComponentTranslation("screenshot.success", file);

			} catch (IOException e) {

				ShadowMC.log.error("There was a problem saving a screenshot, skipping post event");
				e.printStackTrace();
				return new ChatComponentTranslation("screenshot.failure", e.getMessage());

			}
		}

		return new ChatComponentTranslation("screenshot.canceled");
	}

	private static File getTimestampedPNGFileForDirectory(File directory) {
		String s = dateFormat.format(new Date());
		int i = 1;
		while (true) {
			File f = new File(directory, s + (i == 1 ? "" : "_" + i) + ".png");
			if (!f.exists()) {
				return f;
			}
			i++;
		}
	}

}
