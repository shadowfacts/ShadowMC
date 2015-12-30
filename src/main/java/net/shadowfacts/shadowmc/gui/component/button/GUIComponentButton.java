package net.shadowfacts.shadowmc.gui.component.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.gui.component.GUIComponent;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Function;

/**
 * @author shadowfacts
 */
public abstract class GUIComponentButton extends GUIComponent {

	protected Function<MouseButton, ButtonPressResult> callback;

	public GUIComponentButton(Minecraft mc, int x, int y, int width, int height, Function<MouseButton, ButtonPressResult> callback) {
		super(mc, x, y, width, height);
		this.callback = callback;
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		ButtonPressResult result = callback.apply(button);
		if (result == ButtonPressResult.SUCCESS) {
			mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
		}
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY, MouseButton button) {

	}

	@Override
	public void handleKeyPress(int key, char charTyped) {

	}

	@Override
	public void draw() {
//		TODO: draw button background
		drawButton();
	}

	protected abstract void drawButton();

	public enum ButtonPressResult {
		SUCCESS,
		FAILURE;
	}

}
