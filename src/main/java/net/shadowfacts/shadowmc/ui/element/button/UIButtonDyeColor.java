package net.shadowfacts.shadowmc.ui.element.button;

import lombok.Getter;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumDyeColor;
import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.awt.Color;
import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class UIButtonDyeColor extends UIButtonBase {

	private Consumer<EnumDyeColor> handler;

	@Getter
	private EnumDyeColor color;

	public UIButtonDyeColor(EnumDyeColor color, Consumer<EnumDyeColor> handler, String id, String... classes) {
		super("button-dye-color", id, classes);
		tooltip = list -> list.add(I18n.format("shadowmc.dye." + getColor().getUnlocalizedName()));
		this.color = color;
		this.handler = handler;
	}

	public UIButtonDyeColor(Consumer<EnumDyeColor> handler, String id, String... classes) {
		this(EnumDyeColor.WHITE, handler, id, classes);
	}

	@Override
	protected boolean handlePress(int mouseX, int mouseY, MouseButton button) {
		color = EnumUtils.getNextValue(color);
		handler.accept(color);
		return true;
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		super.draw(mouseX, mouseY);
	}

	@Override
	protected void drawButton(int x, int y, UIDimensions dimensions, int mouseX, int mouseY) {
		UIHelper.drawRect(x + 4, y + 4, dimensions.width - 8, dimensions.height - 8, new Color(color.getMapColor().colorValue));
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(20, 20);
	}

}
