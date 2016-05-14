package net.shadowfacts.shadowmc.config.adapter;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class IntegerArrayAdapter implements ConfigTypeAdapter<Configuration, int[]> {

	public static final IntegerArrayAdapter instance = new IntegerArrayAdapter();

	private IntegerArrayAdapter() {
	}

	@Override
	public int[] load(String category, String name, String description, ConfigWrapper<Configuration> config, int[] value) throws ConfigException {
		return config.get().get(category, name, value, description).getIntList();
	}

}
