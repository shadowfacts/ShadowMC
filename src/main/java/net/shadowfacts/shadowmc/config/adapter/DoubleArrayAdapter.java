package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class DoubleArrayAdapter implements ConfigTypeAdapter<Configuration, double[]> {

	public static final DoubleArrayAdapter instance = new DoubleArrayAdapter();

	private DoubleArrayAdapter() {
	}

	@Override
	public double[] load(String category, String name, String description, ConfigWrapper<Configuration> config, double[] value) throws ConfigException {
		return config.get().get(category, name, value, description).getDoubleList();
	}

}
