package net.shadowfacts.shadowmc.ui.element;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.style.UIOrientation;
import net.shadowfacts.shadowmc.ui.util.UIHelper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author shadowfacts
 */
public class UITexturedBarIndicator extends UIBarIndicator {

	protected static final ResourceLocation TEXTURE = new ResourceLocation("shadowmc", "textures/gui/widgets.png");

	public UITexturedBarIndicator(Supplier<Float> levelSupplier, Consumer<List<String>> tooltip, String id, String... classes) {
		super(levelSupplier, tooltip, id, classes);
		setStyle(UIAttribute.PRIMARY_COLOR, new Color(0xFF00007, true));
		setStyle(UIAttribute.SECONDARY_COLOR, new Color(0x330000BB, true));
	}

	public UITexturedBarIndicator(Supplier<Float> levelSupplier, String id, String... classes) {
		this(levelSupplier, ImmutableList::of, id, classes);
	}

	@Override
	public UIDimensions getMaxDimensions() {
		return new UIDimensions(20, UIDimensions.max().height);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		float level = levelSupplier.get();

		UIHelper.bindTexture(TEXTURE);

		Color primaryColor = getStyle(UIAttribute.PRIMARY_COLOR);
		Color secondaryColor = getStyle(UIAttribute.SECONDARY_COLOR);


		UIOrientation orientation = getStyle(UIAttribute.ORIENTATION);
		if (orientation == UIOrientation.VERTICAL) {
			int filled = Math.min((int)(level * dimensions.height), dimensions.height);

			for (int i = 0; i < dimensions.height / 50; i++) {
				UIHelper.drawTexturedRect(x, y + (i * 50), 0, 20, dimensions.width, i * 50);
			}
			if (dimensions.height % 50 != 0) {
				UIHelper.drawTexturedRect(x, y + dimensions.height - (dimensions.height % 50), 0, 20, dimensions.width, dimensions.height % 50);

			}

			UIHelper.drawRect(x, y + (dimensions.height - filled), dimensions.width, filled, primaryColor, 0.005f);
			UIHelper.drawRect(x, y, dimensions.width, dimensions.height - filled, secondaryColor, 0.005f);
		} else {
			int filled = Math.min((int)(level * dimensions.width), dimensions.width);

			for (int i = 0; i < dimensions.width / 50; i++) {
				GlStateManager.pushMatrix();
				GlStateManager.rotate(90, 0, 0, 1);
				UIHelper.drawTexturedRect(x + (i * 50), y, 0, 20, i * 50, dimensions.height);
				GlStateManager.popMatrix();
			}

			if (dimensions.width % 50 != 0) {
				GlStateManager.pushMatrix();
				GlStateManager.rotate(90, 0, 0, 1);
				UIHelper.drawTexturedRect(x + dimensions.width - (dimensions.width % 50), y, 0, 20, dimensions.width % 50, dimensions.height);
				GlStateManager.popMatrix();
			}

			UIHelper.drawRect(x + dimensions.width - filled, y, filled, dimensions.height, primaryColor);
			UIHelper.drawRect(x, y, dimensions.width - filled, dimensions.height, secondaryColor);
		}

		if (UIHelper.isWithinBounds(mouseX, mouseY, x, y, dimensions)) {
			List<String> tooltip = new ArrayList<>();
			this.tooltip.accept(tooltip);
			if (!tooltip.isEmpty()) {
				UIHelper.drawHoveringText(tooltip, mouseX, mouseY);
			}
		}
	}

}
