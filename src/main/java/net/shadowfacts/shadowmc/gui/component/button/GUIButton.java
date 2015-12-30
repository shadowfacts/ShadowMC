package net.shadowfacts.shadowmc.gui.component.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.gui.component.GUIComponent;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public abstract class GUIButton extends GUIComponent {

	public GUIButton(Minecraft mc, int x, int y, int width, int height) {
		super(mc, x, y, width, height);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		boolean result = handlePress(button);
		if (result) {
			mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
		}
	}

	protected abstract boolean handlePress(MouseButton button);

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

}
