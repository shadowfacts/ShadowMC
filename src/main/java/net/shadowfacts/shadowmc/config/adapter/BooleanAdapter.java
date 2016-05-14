package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class BooleanAdapter implements ConfigTypeAdapter<Configuration, Boolean> {

	public static final BooleanAdapter instance = new BooleanAdapter();

	private BooleanAdapter() {
	}

	@Override
	public Boolean load(String category, String name, String description, ConfigWrapper<Configuration> config, Boolean value) throws ConfigException {
		return config.get().get(category, name, value, description).getBoolean();
	}

}
