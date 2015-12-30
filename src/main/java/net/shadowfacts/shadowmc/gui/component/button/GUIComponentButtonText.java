package net.shadowfacts.shadowmc.gui.component.button;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Function;

/**
 * @author shadowfacts
 */
public class GUIComponentButtonText extends GUIComponentButton {

	@Getter @Setter
	protected String text;

	public GUIComponentButtonText(Minecraft mc, int x, int y, int width, int height, Function<MouseButton, ButtonPressResult> callback, String text) {
		super(mc, x, y, width, height, callback);
		this.text = text;
	}

	@Override
	protected void drawButton() {
		drawCenteredText(text, x, x + width, y);
	}

}
