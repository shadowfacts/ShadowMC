package net.shadowfacts.shadowmc.ui.element.button;

import lombok.Setter;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIMouseInteractable;
import net.shadowfacts.shadowmc.ui.element.UIElementBase;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public abstract class UIButtonBase extends UIElementBase implements UIMouseInteractable {

	protected static final ResourceLocation TEXTURE = new ResourceLocation("shadowmc", "textures/gui/widgets.png");

	@Setter
	protected boolean enabled = true;

	@Setter
	protected Consumer<List<String>> tooltip;

	public UIButtonBase(String type, String id, String... classes) {
		super(type, id, classes);
	}

	protected abstract boolean handlePress(int mouseX, int mouseY, MouseButton button);

	protected abstract void drawButton(int mouseX, int mouseY);

	@Override
	public void mouseClickDown(int mouseX, int mouseY, MouseButton button) {
		if (enabled) {
			boolean result = handlePress(mouseX, mouseY, button);
			if (result) {
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1));
			}
		}
	}

	@Override
	public abstract UIDimensions getMinDimensions();

	@Override
	public abstract UIDimensions getPreferredDimensions();

	@Override
	public void draw(int mouseX, int mouseY) {
		if (getStyle(UIAttribute.BACKGROUND_ENABLED)) {
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			UIHelper.bindTexture(TEXTURE);

			int v;
			if (enabled) {
				if (UIHelper.isWithinBounds(mouseX, mouseY, this)) {
					v = 236;
				} else {
					v = 216;
				}
			} else {
				v = 196;
			}

			// top left
			UIHelper.drawTexturedRect(x, y, 0, v, dimensions.width / 2, dimensions.height / 2);
			// bottom left
			UIHelper.drawTexturedRect(x, y + dimensions.height / 2, 0, v + 20 - dimensions.height / 2, dimensions.width / 2, dimensions.height / 2);
			// top right
			UIHelper.drawTexturedRect(x + dimensions.width / 2, y, 256 - dimensions.width / 2, v, dimensions.width / 2, dimensions.height / 2);
			// bottom right
			UIHelper.drawTexturedRect(x + dimensions.width / 2, y + dimensions.height / 2, 256 - dimensions.width / 2, v + 20 - dimensions.height / 2, dimensions.width / 2, dimensions.height / 2);

			GlStateManager.disableBlend();
		}
		drawButton(mouseX, mouseY);
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
		if (tooltip != null && UIHelper.isWithinBounds(mouseX, mouseY, this)) {
			List<String> tooltip = new ArrayList<>();
			this.tooltip.accept(tooltip);
			if (!tooltip.isEmpty()) {
				UIHelper.drawHoveringText(tooltip, mouseX, mouseY);
			}
		}
	}

}
