package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class IntegerAdapter implements ConfigTypeAdapter<Configuration, Integer> {

	public static final IntegerAdapter instance = new IntegerAdapter();

	private IntegerAdapter() {
	}

	@Override
	public Integer load(String category, String name, String description, ConfigWrapper<Configuration> config, Integer value) throws ConfigException {
		return config.get().get(category, name, value, description).getInt();
	}

}
