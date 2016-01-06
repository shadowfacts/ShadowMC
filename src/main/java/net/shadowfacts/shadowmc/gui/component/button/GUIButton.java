package net.shadowfacts.shadowmc.gui.component.button;

import lombok.Getter;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.gui.component.GUIComponent;
import net.shadowfacts.shadowmc.util.MouseButton;

/**
 * @author shadowfacts
 */
public abstract class GUIButton extends GUIComponent {

	protected static final ResourceLocation bgTexture = new ResourceLocation("minecraft", "textures/gui/widgets.png");

	@Getter
	protected boolean drawBackground = true;

	public GUIButton(int x, int y, int width, int height) {
		super(x, y, width, height);
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
	public void draw(int mouseX, int mouseY) {
		if (drawBackground) {
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			int i = isWithinBounds(mouseX, mouseY) ? 40 : 20;
			bindTexture(bgTexture);
			drawTexturedRect(x, y, 0, 46 + i, width / 2, height);
			drawTexturedRect(x + width / 2, y, 200 - width / 2, 46 + i, width / 2, height);
		}

		drawButton();
	}

	protected abstract void drawButton();

	public GUIButton setDrawBackground(boolean val) {
		drawBackground = val;
		return this;
	}

}
