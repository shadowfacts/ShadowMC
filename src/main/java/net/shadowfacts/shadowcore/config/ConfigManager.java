package net.shadowfacts.shadowcore.config;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.shadowcore.ShadowCore;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Manages all config files for the new configuration system.
 * @author shadowfacts
 */
public class ConfigManager {
//	The instance
	public static ConfigManager instance = new ConfigManager();


	public String configDirPath;

	private Map<String, Class> configs = new HashMap<String, Class>();


	public void register(String name, Class config) {
		if (config.isAnnotationPresent(Config.class)) {
			if (configs.containsKey(name)) {
				ShadowCore.log.error("Someone attempted to register a config class (%s) whose name was already registered.", name);
				return;
			} else if (configs.containsValue(config)) {
				ShadowCore.log.error("Someone attempted to register a config class (%s) that was already registered.", name);
				return;
			} else {
				configs.put(name, config);
				load(name);
			}
		} else {
			ShadowCore.log.error("Someone attempted to register a config class (%s) that was missing the @Config annotation.", name);
			return;
		}
	}

	public ArrayList<String> getLoadedConfigs() {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(configs.keySet());
		return list;
	}

	public boolean isConfigLoaded(String name) {
		return configs.containsKey(name);
	}

	public void loadAll() {
		for (String s : configs.keySet()) {
			load(s);
		}
	}

	public void load(String name) {
		Class configClass = configs.get(name);

		if (configClass == null) {
			ShadowCore.log.error(String.format("The config class %s was null. This should not be happening, report this immediately!", name));
			return;
		}

		if (configClass.getAnnotation(Config.class) != null) {

			Config configClassAnnotation = (Config)configClass.getAnnotation(Config.class);

			String configName = configClassAnnotation.name();

			String path;
			if (configClassAnnotation.useSubFolder()) {
				path = this.configDirPath + "/" + configClassAnnotation.folder() + "/" + configClassAnnotation.name() + ".cfg";
			} else {
				path = this.configDirPath + "/" + configClassAnnotation.name() + ".cfg";
			}

			Configuration config = new Configuration(new File(path));

			config.load();

			for (Field f : configClass.getDeclaredFields()) {

				if (f.getAnnotation(ConfigProperty.class) != null) {

					ConfigProperty prop = (ConfigProperty)f.getAnnotation(ConfigProperty.class);

					String propertyName = f.getName();


					try {

						if (f.getType() == boolean.class) {

							boolean val = config.getBoolean(propertyName, prop.category(), f.getBoolean(null), prop.comment());
							f.setBoolean(null, val);

						} else if (f.getType() == int.class) {

							int val = config.getInt(propertyName, prop.category(), f.getInt(null), prop.intMin(), prop.intMax(), prop.comment());
							f.setInt(null, val);

						} else if (f.getType() == float.class) {

							float val = config.getFloat(propertyName, prop.category(), f.getFloat(null), prop.floatMin(), prop.floatMax(), prop.comment());
							f.setFloat(null, val);

						} else if (f.getType() == String.class) {

							String val;

							String[] defaults = {"DEFAULT"};

							if (prop.stringValidValues() != defaults) { // String with a list of possible values
								val = config.getString(propertyName, prop.category(), (String)f.get(null), prop.comment(), prop.stringValidValues());
							} else {
								val = config.getString(propertyName, prop.category(), (String) f.get(null), prop.comment());
							}

							f.set(null, val);

						} else if (f.getType() == String[].class) {

							String[] val;

							String[] defaults = {"DEFAULT"};

							if (prop.stringValidValues() != defaults) { // String[] with list of allowed values
								val = config.getStringList(propertyName, prop.category(), (String[])f.get(null), prop.comment(), prop.stringValidValues());
							} else {
								val = config.getStringList(propertyName, prop.category(), (String[])f.get(null), prop.comment());
							}

							f.set(null, val);

						}

					} catch (Exception e) {
						ShadowCore.log.error("There was a problem getting a value for a config field.");
						e.printStackTrace();
					}
				}
			}

			config.save();

		}

	}



}
