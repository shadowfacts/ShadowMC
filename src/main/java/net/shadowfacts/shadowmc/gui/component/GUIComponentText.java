package net.shadowfacts.shadowmc.gui.component;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.Color;

/**
 * @author shadowfacts
 */
public class GUIComponentText extends GUIComponent {

	@Getter @Setter
	private String text;

	private Color color = Color.WHITE;

	public GUIComponentText(int x, int y, String text) {
		super(x, y, Minecraft.getMinecraft().fontRendererObj.getStringWidth(text), 8);
		this.text = text;
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		drawText(getText(), x, y, color);
	}

	public GUIComponentText setColor(Color color) {
		this.color = color;
		return this;
	}
}
