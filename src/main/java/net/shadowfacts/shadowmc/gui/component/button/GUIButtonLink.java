package net.shadowfacts.shadowmc.gui.component.button;

import net.minecraft.util.text.TextFormatting;
import net.shadowfacts.shadowlib.util.DesktopUtils;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author shadowfacts
 */
public class GUIButtonLink extends GUIButtonText {

	protected URI link;

	public GUIButtonLink(int x, int y, int width, int height, String text, URI link) {
		super(x, y, width, height, null, text);
		this.link = link;
		callback = this::openLink;
		color = Color.BLUE;
	}

	public GUIButtonLink(int x, int y, int width, int height, String text, String link) {
		this(x, y, width, height, text, (URI)null);
		try {
			this.link = new URI(link);
		} catch (URISyntaxException e) {
			ShadowMC.log.error("Couldn't create URI for GUIButtonLink, disabling button");
			e.printStackTrace();
			enabled = false;
		}
	}

	public boolean openLink(GUIButtonText button, MouseButton mouseButton) {
		DesktopUtils.openWebpage(link);
		return true;
	}

	@Override
	protected void drawButton() {
		drawCenteredText(TextFormatting.UNDERLINE + text + TextFormatting.RESET, x, x + width, y, y + height, color);
	}
}
