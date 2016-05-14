package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class StringArrayAdapter implements ConfigTypeAdapter<Configuration, String[]> {

	public static final StringArrayAdapter instance = new StringArrayAdapter();

	private StringArrayAdapter() {
	}

	@Override
	public String[] load(String category, String name, String description, ConfigWrapper<Configuration> config, String[] value) throws ConfigException {
		return config.get().get(category, name, value, description).getStringList();
	}

}
