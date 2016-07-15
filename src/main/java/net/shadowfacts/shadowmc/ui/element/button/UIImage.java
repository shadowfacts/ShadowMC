package net.shadowfacts.shadowmc.ui.element.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.element.UIElementBase;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

import java.awt.image.BufferedImage;

/**
 * @author shadowfacts
 */
public class UIImage extends UIElementBase {

	private ResourceLocation texture;
	private int u;
	private int v;
	private int width;
	private int height;

	public UIImage(ResourceLocation texture, int u, int v, int width, int height, String id, String... classes) {
		super("image", id, classes);
		this.texture = texture;
		this.u = u;
		this.v = v;
		this.width = width;
		this.height = height;
	}

	public UIImage(ResourceLocation texture, int width, int height, String id, String... classes) {
		this(texture, 0, 0, width, height, id, classes);
	}

	public UIImage(String imageId, BufferedImage image, String id, String... classes) {
		this(Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(imageId, new DynamicTexture(image)), 0, 0, image.getWidth(), image.getHeight(), id, classes);
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(width, height);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		UIHelper.bindTexture(texture);
		UIHelper.drawTexturedRect(x, y, u, v, width, height);
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {

	}

}
