package net.shadowfacts.shadowmc.gui.component;

import lombok.Getter;
import net.minecraft.client.Minecraft;

/**
 * @author shadowfacts
 */
public class GUIComponentText extends GUIComponent {

	@Getter
	private String text;

	public GUIComponentText(Minecraft mc, int x, int y, String text) {
		super(mc, x, y, mc.fontRendererObj.getStringWidth(text), 8);
		this.text = text;
	}

	@Override
	public void draw() {
		mc.fontRendererObj.drawString(getText(), x, y, 0xffffff);
	}
}
