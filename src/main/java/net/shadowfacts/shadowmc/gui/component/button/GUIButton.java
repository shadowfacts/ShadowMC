package net.shadowfacts.shadowmc.gui.component.button;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.shadowfacts.shadowmc.gui.component.GUIComponent;
import net.shadowfacts.shadowmc.gui.handler.ClickHandler;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public abstract class GUIButton extends GUIComponent implements ClickHandler {

	protected boolean enabled = true;

	protected boolean drawBackground = true;

	public GUIButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		if (enabled) {
			boolean result = handlePress(button);
			if (result) {
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			}
		}
	}

	protected abstract boolean handlePress(MouseButton button);

	@Override
	public void draw(int mouseX, int mouseY) {
		if (drawBackground) {
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			bindTexture(widgetTextures);
			if (enabled) {
				if (isWithinBounds(mouseX, mouseY)) { // hovered
					drawTexturedRect(x, y, 0, 236, width / 2, height);
					drawTexturedRect(x + width / 2, y, 256 - width / 2, 236, width / 2, height);
				} else { // normal
					drawTexturedRect(x, y, 0, 216, width / 2, height);
					drawTexturedRect(x + width / 2, y, 256 - width / 2, 216, width / 2, height);
				}
			} else { // disabled
				drawTexturedRect(x, y, 0, 196, width / 2, height);
				drawTexturedRect(x + width / 2, y, 256 - width / 2, 196, width / 2, height);
			}
			GlStateManager.disableBlend();
		}

		drawButton();
	}

	protected abstract void drawButton();

	public GUIButton setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
		return this;
	}

	public GUIButton setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

}
