package net.shadowfacts.shadowmc.ui.element;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

import java.util.List;

/**
 * @author shadowfacts
 */
public class UIFluidIndicator extends UIElementBase {

	private IFluidTank tank;

	public UIFluidIndicator(IFluidTank tank, String id, String... classes) {
		super("fluid-indicator", id, classes);
		this.tank = tank;
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(20, 100);
	}

	@Override
	public UIDimensions getMaxDimensions() {
		return new UIDimensions(20, UIDimensions.max().height);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		UIHelper.drawRect(x, y, dimensions.width, dimensions.height, getStyle(UIAttribute.BORDER_COLOR));

		if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
			float level = tank.getFluidAmount() / (float)tank.getCapacity();
			int height = dimensions.height - 4;
			int filled = Math.min((int)(level * height), height);

			TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill(tank.getFluid()).toString());

			int x = this.x + 2;
			int y = this.y + (height - filled) + 2;

			float minU = sprite.getInterpolatedU(0);
			float minV = sprite.getInterpolatedV(0);
			float maxU = sprite.getInterpolatedU(16);
			float maxV = sprite.getInterpolatedV(16);

			for (int i = 0; i < filled / 16; i++) {
				UIHelper.drawFluidQuad(x, y + (i * 16), 16, 16, minU, minV, maxU, maxV);
			}

			if (filled % 16 != 0) {
				UIHelper.drawFluidQuad(x, y + filled - (filled % 16), 16, filled % 16, minU, minV, maxU, sprite.getInterpolatedV(filled % 16));
			}
		}
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
		if (UIHelper.isWithinBounds(mouseX, mouseY, this)) {
			List<String> tooltip;
			if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
				tooltip = ImmutableList.of(String.format("%s %d / %d mB", tank.getFluid().getLocalizedName(), tank.getFluidAmount(), tank.getCapacity()));
			} else {
				tooltip = ImmutableList.of(I18n.format("shadowmc.gui.empty"));
			}
			UIHelper.drawHoveringText(tooltip, mouseX, mouseY);
		}
	}

}
