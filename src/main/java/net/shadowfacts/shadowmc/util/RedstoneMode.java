package net.shadowfacts.shadowmc.util;

/**
 * @author shadowfacts
 */
public enum RedstoneMode {

	ALWAYS("redstone.always"),
	NEVER("redstone.never"),
	HIGH("redstone.high"),
	LOW("redstone.low"),
	PULSE("redstone.pulse");

	private String unlocalized;

	RedstoneMode(String unlocalized) {
		this.unlocalized = unlocalized;
	}

	public String localize() {
		return StringHelper.localize(unlocalized);
	}

}
