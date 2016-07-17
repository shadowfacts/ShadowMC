package net.shadowfacts.shadowmc.ui.element.button;

import lombok.Setter;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.BiFunction;

/**
 * @author shadowfacts
 */
public class UIButtonText extends UIButtonBase {

	@Setter
	protected String text;

	protected BiFunction<UIButtonText, MouseButton, Boolean> callback;

	public UIButtonText(String text, BiFunction<UIButtonText, MouseButton, Boolean> callback, String id, String... classes) {
		super("button-text", id, classes);
		this.text = text;
		this.callback = callback;
	}

	@Override
	protected boolean handlePress(int mouseX, int mouseY, MouseButton button) {
		return callback.apply(this, button);
	}

	@Override
	protected void drawButton(int mouseX, int mouseY) {
		UIHelper.drawCenteredText(UIHelper.styleText(text, this), x, y, x + dimensions.width, y + dimensions.height, getStyle(UIAttribute.TEXT_COLOR), getStyle(UIAttribute.TEXT_SHADOW));
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(mc.fontRendererObj.getStringWidth(UIHelper.styleText(text, this)) + 10, mc.fontRendererObj.FONT_HEIGHT + 10);
	}

}
