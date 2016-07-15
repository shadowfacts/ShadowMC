package net.shadowfacts.shadowmc.ui.element.view;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

/**
 * @author shadowfacts
 */
public class UIListView extends UIView {

	private int preferredWidth;
	private int preferredHeight;

	private int scroll;

	private int totalHeight;

	public UIListView(int preferredWidth, int preferredHeight, String id, String... classes) {
		super("list", id, classes);
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;
	}

	private void setScroll(int scroll) {
		if (scroll > 0) {
			scroll = 0;
		} else if ((scroll + 1) * 10 < -(totalHeight - preferredHeight)) {
			scroll = (-(totalHeight - preferredHeight) / 10) - 1;
		}
		int originalScroll = this.scroll;
		this.scroll = scroll;

		for (UIElement e : children) {
			e.setY(e.getY() - (originalScroll * 10) + (scroll * 10));
		}
	}

	@Override
	public void mouseScroll(int mouseX, int mouseY, int delta) {
		if (UIHelper.isWithinBounds(mouseX, mouseY, this)) {
			setScroll(scroll + Integer.signum(delta));
		}
	}

	@Override
	public void mouseMove(int mouseX, int mouseY, MouseButton button, long timeSinceLastClick) {
		int barWidth = getStyle(UIAttribute.SCROLLBAR_WIDTH);
		if (UIHelper.isWithinBounds(mouseX, mouseY, x + dimensions.width - barWidth, y, x + dimensions.width, y + dimensions.height)) {

			int dist = mouseY - y;
			int maxScroll = (-(totalHeight - preferredHeight) / 10) - 1;
			setScroll((int)(maxScroll * (dist / (float)preferredHeight)));

		} else {
			super.mouseMove(mouseX, mouseY, button, timeSinceLastClick);
		}
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		int barWidth = getStyle(UIAttribute.SCROLLBAR_WIDTH);

		UIHelper.drawRect(x + dimensions.width - barWidth - 1, y - 1, barWidth + 2, dimensions.height + 2, getStyle(UIAttribute.SCROLLBAR_BACKGROUND_COLOR));
		int scrollbarTop = (int)(((scroll * 10) / (float)totalHeight) * preferredHeight);
		int scrollbarHeight = (int)((preferredHeight / (float)totalHeight) * preferredHeight);
		Color secondary = getStyle(UIAttribute.SCROLLBAR_SCEONDARY_COLOR);
		Color primary = getStyle(UIAttribute.SCROLLBAR_PRIMARY_COLOR);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, y - scrollbarTop * 2, 0);
		UIHelper.drawRect(x + dimensions.width - barWidth, scrollbarTop, barWidth, scrollbarHeight, secondary);
		UIHelper.drawRect(x + dimensions.width - barWidth, scrollbarTop, barWidth - 1, scrollbarHeight - 1, primary);
		GL11.glPopMatrix();

		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

		int width = (dimensions.width) * res.getScaleFactor();
		int height = (dimensions.height) * res.getScaleFactor();
		int x  = (this.x) * res.getScaleFactor();
		int y = mc.displayHeight - ((this.y) * res.getScaleFactor()) - height;

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(x, y, width, height);

		super.draw(mouseX, mouseY);

		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GL11.glPopMatrix();
	}

	@Override
	public void layout(int minX, int maxX, int minY, int maxY) {
		super.layout(minX, maxX, minY, maxY);

		int currentY = 0;
		for (UIElement e : children) {
			int marginLeft = e.getStyle(UIAttribute.MARGIN_LEFT);
			int marginRight = e.getStyle(UIAttribute.MARGIN_RIGHT);
			UIDimensions elementDimensions = e.getPreferredDimensions();
			e.layout(marginLeft, elementDimensions.width - marginRight - 40 - getStyle(UIAttribute.SCROLLBAR_WIDTH) - 2, currentY, currentY + elementDimensions.height);
			e.setX(x + e.getX());
			e.setY(y + currentY);
			currentY += elementDimensions.height;
		}

		totalHeight = children.stream()
				.map(UIElement::getDimensions)
				.mapToInt(dimensions -> dimensions.height)
				.sum();
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
	public UIDimensions getMaxDimensions() {
		return getPreferredDimensions();
	}

}
