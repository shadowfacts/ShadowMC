package net.shadowfacts.shadowmc.ui.element;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.style.UIHorizontalLayoutMode;
import net.shadowfacts.shadowmc.ui.style.UIVerticalLayoutMode;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

import java.awt.Color;

/**
 * @author shadowfacts
 */
public class UILabel extends UIElementBase {

	@Setter
	private String text;

	private int width;

	public UILabel(String text, int width, String id, String... classes) {
		super("label", id, classes);
		this.text = text;
		this.width = width;
	}

	public UILabel(String text, String id, String... classes) {
		this(text, Minecraft.getMinecraft().fontRendererObj.getStringWidth(text), id, classes);
	}

	@Override
	public UIDimensions getMinDimensions() {
		return new UIDimensions(mc.fontRendererObj.getStringWidth(UIHelper.styleText(text, this)), mc.fontRendererObj.FONT_HEIGHT);
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(width, mc.fontRendererObj.FONT_HEIGHT);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		UIHorizontalLayoutMode horizontalLayoutMode = getStyle(UIAttribute.TEXT_HORIZONTAL_LAYOUT);
		UIVerticalLayoutMode verticalLayoutMode = getStyle(UIAttribute.TEXT_VERTICAL_LAYOUT);
		Color color = getStyle(UIAttribute.TEXT_COLOR);
		boolean shadow = getStyle(UIAttribute.TEXT_SHADOW);

		int xOffset;
		int yOffset;

		switch (horizontalLayoutMode) {
			default:
			case LEFT:
				xOffset = 0;
				break;
			case CENTER:
				xOffset = (dimensions.width - mc.fontRendererObj.getStringWidth(text)) / 2;
				break;
			case RIGHT:
				xOffset = dimensions.width - mc.fontRendererObj.getStringWidth(text);
				break;
		}

		switch (verticalLayoutMode) {
			default:
			case TOP:
				yOffset = 0;
				break;
			case CENTER:
				yOffset = -dimensions.height / 2;
				break;
			case BOTTOM:
				yOffset = dimensions.height - mc.fontRendererObj.FONT_HEIGHT;
				break;
		}

		mc.fontRendererObj.drawString(UIHelper.styleText(text, this), x + xOffset, y + yOffset, UIHelper.toARGB(color), shadow);
		GlStateManager.color(1, 1, 1);
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {

	}

}
