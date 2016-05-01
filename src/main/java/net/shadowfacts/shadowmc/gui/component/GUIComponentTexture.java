package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.util.ResourceLocation;

/**
 * @author shadowfacts
 */
public class GUIComponentTexture extends GUIComponent {

	private ResourceLocation texture;
	private int u;
	private int v;

	public GUIComponentTexture(int x, int y, int width, int height, ResourceLocation texture, int u, int v) {
		super(x, y, width, height);
		this.texture = texture;
		this.u = u;
		this.v = v;
	}

	public GUIComponentTexture(int x, int y, int width, int height, ResourceLocation texture) {
		this(x, y, width, height, texture, 0, 0);
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		bindTexture(texture);
		drawTexturedRect(x, y, u, v, width, height);
	}

}
