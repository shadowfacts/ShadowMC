package net.shadowfacts.shadowcore.config;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.shadowcore.ShadowCore;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Manages all config files for the new configuration system.
 * @author shadowfacts
 */
public class ConfigManager {
//	The instance
	public static ConfigManager instance = new ConfigManager();


	public String configDirPath;

	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Class> classes = new ArrayList<Class>();



	public void register(String name, Class config) {
		if (config.isAnnotationPresent(Config.class) && !names.contains(name) && !classes.contains(config)) {
			names.add(name);
			classes.add(config);
		} else {
			ShadowCore.log.error(String.format("Someone attempted to register an invalid config (%s).", name));
		}
	}

	public ArrayList<String> getLoadedConfigs() {
		return names;
	}

	public boolean isConfigLoaded(String name) {
		return names.contains(name);
	}

	public void loadAll() {
		for (String s : this.names) {
			this.load(s);
		}
	}

	public void load(String name) {

		Class configClass = classes.get(names.indexOf(name));

		if (configClass == null) {
			ShadowCore.log.error("Someone tried to load a config that was not registered, " + name);
			return;
		}

		if (configClass != null) {
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

								int val = config.getInt(propertyName, prop.category(), f.getInt(null), prop.intMin(), prop.intMin(), prop.comment());
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
		} else {
			ShadowCore.log.error(String.format("The config class %s was null. This should not be happening, report this immediately!", name));
		}

//		Configuration config = new Configuration(new File(this.configDirPath + "/" + ))

	}



}
