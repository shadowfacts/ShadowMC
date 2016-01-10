package net.shadowfacts.shadowmc.gui.component.button;

import lombok.Getter;
import lombok.Setter;
import net.shadowfacts.shadowlib.util.EnumUtils;
import net.shadowfacts.shadowmc.util.MouseButton;
import net.shadowfacts.shadowmc.util.RedstoneMode;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class GUIButtonRedstoneMode extends GUIButton {

	@Getter @Setter
	protected RedstoneMode redstoneMode;

	protected Consumer<RedstoneMode> handler;

	public GUIButtonRedstoneMode(int x, int y, RedstoneMode redstoneMode, Consumer<RedstoneMode> handler) {
		super(x, y, 20, 20);
		this.redstoneMode = redstoneMode;
		this.handler = handler;
	}

	public GUIButtonRedstoneMode(int x, int y, Consumer<RedstoneMode> handler) {
		this(x, y, RedstoneMode.ALWAYS, handler);
	}

	@Override
	protected boolean handlePress(MouseButton button) {
		setRedstoneMode(EnumUtils.getNextValue(redstoneMode));
		handler.accept(redstoneMode);
		return true;
	}

	@Override
	protected void drawButton() {
		bindTexture(widgetTextures);
		switch (redstoneMode) {
			case ALWAYS:
				drawTexturedRect(x, y, 80, 0, width, height);
				break;
			case NEVER:
				drawTexturedRect(x, y, 100, 0, width, height);
				break;
			case HIGH:
				drawTexturedRect(x, y, 60, 0, width, height);
				break;
			case LOW:
				drawTexturedRect(x, y, 40, 0, width, height);
				break;
			case PULSE:
				drawTexturedRect(x, y, 120, 0, width, height);
				break;
		}
	}

	@Override
	public List<String> getTooltip() {
		return Collections.singletonList(redstoneMode.localize());
	}
}
