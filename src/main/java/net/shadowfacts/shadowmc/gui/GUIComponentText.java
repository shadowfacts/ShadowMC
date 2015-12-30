package net.shadowfacts.shadowmc.gui;

import lombok.Getter;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class GUIComponentText extends AbstractGUI {

	@Getter
	private String text;

	public GUIComponentText(Minecraft mc, int x, int y, String text) {
		super(mc, x, y, mc.fontRendererObj.getStringWidth(text), 8);
		this.text = text;
	}

	@Override
	public void draw() {
		mc.fontRendererObj.drawString(getText(), getX(), getY(), 0xffffff);
	}

	@Override
	public List<AbstractGUI> getChildren() {
		return new ArrayList<>();
	}
}
