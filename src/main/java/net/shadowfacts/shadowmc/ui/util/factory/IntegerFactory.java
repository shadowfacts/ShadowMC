package net.shadowfacts.shadowmc.ui.util.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerFactory implements ValueFactory<Integer> {

	public static final IntegerFactory INSTANCE = new IntegerFactory();

	@Override
	public Integer create(String s, Integer defaultVal) {
		if (s.endsWith("px")) s = s.substring(0, s.length() - 2);

		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return defaultVal;
		}
	}

}
