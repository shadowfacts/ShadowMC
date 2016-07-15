package net.shadowfacts.shadowmc.ui.util.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanFactory implements ValueFactory<Boolean> {

	public static final BooleanFactory INSTANCE = new BooleanFactory();

	@Override
	public Boolean create(String s, Boolean defaultVal) {
		return Boolean.parseBoolean(s);
	}

}
