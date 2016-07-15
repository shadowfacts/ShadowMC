package net.shadowfacts.shadowmc.ui.element.button;

import net.shadowfacts.shadowlib.util.DesktopUtils;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author shadowfacts
 */
public class UIButtonLink extends UIButtonText {

	protected URI link;

	public UIButtonLink(String text, URI link, String id, String... classes) {
		super(text, null, id, classes);
		this.link = link;
		this.callback = this::openLink;
		setStyle(UIAttribute.TEXT_COLOR, Color.BLUE);
		setStyle(UIAttribute.TEXT_UNDERLINE, true);
	}

	public UIButtonLink(String text, String link, String id, String... classes) {
		this(text, (URI)null, id, classes);
		try {
			this.link = new URI(link);
		} catch (URISyntaxException e) {
			ShadowMC.log.error("Couldn't create URI for UIButtonLink, disabling button", e);
			enabled = false;
		}
	}

	private boolean openLink(UIButtonText button, MouseButton mouseButton) {
		DesktopUtils.openWebpage(link);
		return true;
	}

}
