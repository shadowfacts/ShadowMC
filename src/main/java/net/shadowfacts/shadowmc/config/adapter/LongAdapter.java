package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class LongAdapter implements ConfigTypeAdapter<Configuration, Long> {

	public static final LongAdapter instance = new LongAdapter();

	private LongAdapter() {
	}

	@Override
	public Long load(String category, String name, String description, ConfigWrapper<Configuration> config, Long value) throws ConfigException {
		Property prop = config.get().get(category, name, value, description);
		return prop.getLong(value);
	}

}
