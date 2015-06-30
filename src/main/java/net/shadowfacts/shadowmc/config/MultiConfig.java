package net.shadowfacts.shadowmc.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Internal class used for having multiple {@link Config} objects in the same file.
 *
 * @author shadowfacts
 */
public class MultiConfig {

	private final String name;
	private ArrayList<Class> configClasses = new ArrayList<Class>();
	private Configuration forgeConfig;

	MultiConfig(String name) {
		this.name = name;

	}

	public ArrayList<Class> getConfigClasses() {
		return configClasses;
	}

	public boolean addClass(Class clazz) {
		return configClasses.add(clazz);
	}

	public boolean addAll(Collection<Class> classes) {
		return configClasses.addAll(classes);
	}

	public boolean hasForgeConfig() {
		return forgeConfig != null;
	}

	public Configuration getForgeConfig() {
		return forgeConfig;
	}

	public void setForgeConfig(Configuration forgeConfig) {
		this.forgeConfig = forgeConfig;
	}
}
