package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class DoubleAdapter implements ConfigTypeAdapter<Configuration, Double> {

	public static final DoubleAdapter instance = new DoubleAdapter();

	private DoubleAdapter() {
	}

	@Override
	public Double load(String category, String name, String description, ConfigWrapper<Configuration> config, Double value) throws ConfigException {
		return config.get().get(category, name, value, description).getDouble();
	}

}
