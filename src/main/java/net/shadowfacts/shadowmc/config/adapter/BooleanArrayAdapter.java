package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class BooleanArrayAdapter implements ConfigTypeAdapter<Configuration, boolean[]> {

	public static final BooleanArrayAdapter instance = new BooleanArrayAdapter();

	private BooleanArrayAdapter() {
	}

	@Override
	public boolean[] load(String category, String name, String description, ConfigWrapper<Configuration> config, boolean[] value) throws ConfigException {
		return config.get().get(category, name, value, description).getBooleanList();
	}

}
