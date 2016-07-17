package net.shadowfacts.shadowmc.ui.element.button;

import lombok.Getter;
import lombok.Setter;
import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;
import net.shadowfacts.shadowmc.util.RedstoneMode;

import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class UIButtonRedstoneMode extends UIButtonBase {

	@Getter @Setter
	protected RedstoneMode mode;

	protected Consumer<RedstoneMode> callback;

	public UIButtonRedstoneMode(RedstoneMode mode, Consumer<RedstoneMode> callback, String id, String... classes) {
		super("button-redstone-mode", id, classes);
		this.mode = mode;
		this.callback = callback;
	}

	public UIButtonRedstoneMode(Consumer<RedstoneMode> callback, String id, String... classes) {
		this(RedstoneMode.ALWAYS, callback, id, classes);
	}

	@Override
	protected boolean handlePress(int mouseX, int mouseY, MouseButton button) {
		if (button == MouseButton.LEFT) {
			mode = EnumUtils.getNextValue(mode);
			callback.accept(mode);
		} else if (button == MouseButton.RIGHT) {
			mode = EnumUtils.getPreviousValue(mode);
			callback.accept(mode);
		}
		return true;
	}

	@Override
	protected void drawButton(int mouseX, int mouseY) {
		UIHelper.bindTexture(TEXTURE);
		int u;
		switch (mode) {
			default:
			case ALWAYS:
				u = 80;
				break;
			case NEVER:
				u = 100;
				break;
			case HIGH:
				u = 60;
				break;
			case LOW:
				u = 40;
				break;
			case PULSE:
				u = 120;
				break;
		}
		UIHelper.drawTexturedRect(x, y, u, 0, dimensions.width, dimensions.height);
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
