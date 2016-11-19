package net.shadowfacts.shadowmc.ui.element;

import lombok.Setter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class UIItemStack extends UIElementBase {

	@Setter
	private ItemStack stack;

	private int preferredWidth;
	private int preferredHeight;

	@Setter
	private boolean tooltip;

	public UIItemStack(ItemStack stack, int width, int height, String id, String... classes) {
		super("item-stack", id, classes);
		this.stack = stack;
		this.preferredWidth = width;
		this.preferredHeight = height;
	}

	public UIItemStack(ItemStack stack, String id, String... classes) {
		this(stack, 16, 16, id, classes);
	}

	public UIItemStack(Item item, int width, int height, String id, String... classes) {
		this(new ItemStack(item), width, height, id, classes);
	}

	public UIItemStack(Item item, String id, String... classes) {
		this(item, 16, 16, id, classes);
	}

	public UIItemStack(Block block, int width, int height, String id, String... classes) {
		this(new ItemStack(block), width, height, id, classes);
	}

	public UIItemStack(Block block, String id, String... classes) {
		this(block, 16, 16, id, classes);
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(preferredWidth, preferredHeight);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		GL11.glPushMatrix();
		RenderHelper.enableGUIStandardItemLighting();
		float horizontalScale = preferredWidth / 16f;
		float verticalScale = preferredHeight / 16f;
		GL11.glScalef(horizontalScale, verticalScale, 0);
		mc.getRenderItem().renderItemAndEffectIntoGUI(stack, (int)(x / horizontalScale), (int)(y / horizontalScale));
		GL11.glPopMatrix();
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
		if (tooltip && UIHelper.isWithinBounds(mouseX, mouseY, this)) {
			UIHelper.drawHoveringText(stack.getTooltip(mc.player, mc.gameSettings.advancedItemTooltips), mouseX, mouseY);
		}
	}

}
