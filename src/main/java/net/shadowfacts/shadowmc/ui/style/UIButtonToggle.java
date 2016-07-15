package net.shadowfacts.shadowmc.ui.style;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.element.button.UIButtonBase;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.function.Consumer;

/**
 * @author shadowfacts
 */
public class UIButtonToggle extends UIButtonBase {

	@Setter
	private boolean state;

	protected Consumer<UIButtonToggle> callback;

	public UIButtonToggle(boolean state, Consumer<UIButtonToggle> callback, String id, String... classes) {
		super("button-toggle", id, classes);
		this.state = state;
		this.callback = callback;
	}

	public UIButtonToggle(Consumer<UIButtonToggle> callback, String id, String... classes) {
		this(true, callback, id, classes);
	}

	public boolean getState() {
		return state;
	}

	@Override
	protected boolean handlePress(int mouseX, int mouseY, MouseButton button) {
		state = !state;
		callback.accept(this);
		return true;
	}

	@Override
	protected void drawButton(int x, int y, UIDimensions dimensions, int mouseX, int mouseY) {
		UIHelper.bindTexture(TEXTURE);
		int u;
		if (state) {
			u = 0;
		} else {
			u = 20;
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
