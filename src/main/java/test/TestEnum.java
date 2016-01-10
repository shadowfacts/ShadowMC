package test;

import net.shadowfacts.shadowmc.util.StringHelper;

/**
 * @author shadowfacts
 */
public enum TestEnum {

	THING1("thing1"),
	THING2("thing2");

	private String unlocalized;

	TestEnum(String unlocalized) {
		this.unlocalized = unlocalized;
	}

	public String localize() {
		return StringHelper.localize(unlocalized);
	}

}
