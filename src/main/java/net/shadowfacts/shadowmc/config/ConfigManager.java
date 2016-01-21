package net.shadowfacts.shadowmc.config;

import lombok.Getter;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Manages all config files for the new configuration system.
 * @author shadowfacts
 */
public class ConfigManager {

	public static ConfigManager instance = new ConfigManager();

	public File configDir;

	@Getter
	private HashMap<String, MultiConfig> configs = new HashMap<>();
	@Getter
	private HashMap<String, String> modIdConfigNameMap = new HashMap<>();

    private Logger log = LogManager.getLogger("ShadowMC|ConfigManager");

	public void register(String name, Class clazz, String modId) {

		if (clazz != null) {

			if (clazz.isAnnotationPresent(Config.class)) {

				MultiConfig multiConfig;

				if (!configs.containsKey(name)) {
					multiConfig = new MultiConfig(name);
					configs.put(name, multiConfig);
					modIdConfigNameMap.put(modId, name);
				} else {
					multiConfig = configs.get(name);
				}

				multiConfig.addClass(clazz);

				Config annotation = (Config)clazz.getAnnotation(Config.class);

				if (!multiConfig.hasForgeConfig()) {
					multiConfig.setForgeConfig(new Configuration(new File(annotation.directory() + File.separator + annotation.name() + ".cfg")));
				}

			} else {
				log.error("Someone attempted to register a config class without the @Config annotation (%s). This should not be happening, report this immediately!", name);
				throw new RuntimeException("Attempt to register a not-annotated config class");
			}

		} else {
			log.error("Someone attempted to register a null config class (%s), this should not be happening, report this immediately!", name);
			throw new RuntimeException("Attempt to register a null config class");
		}
	}

	public ArrayList<String> getLoadedConfigs() {
		return new ArrayList<>(configs.keySet());
	}

	public boolean isConfigLoaded(String name) {
		return configs.containsKey(name);
	}

	public Configuration getConfigurationObject(String name) {
		return configs.get(name).getForgeConfig();
	}

	public void loadAll() {
		configs.keySet().forEach(this::load);
	}

	public void load(String name) {
		MultiConfig multiConfig = configs.get(name);

		if (multiConfig == null) {
			log.error("The config %s was null, skipping. This should not be happening, report this immediately!", name);
			throw new RuntimeException("Attempt to load a null config.");
		}

		Configuration config = multiConfig.getForgeConfig();

		for (Class clazz : multiConfig.getConfigClasses()) {
			for (Field f : clazz.getDeclaredFields()) {
				ConfigProperty annotation = f.getAnnotation(ConfigProperty.class);

				if (annotation != null) {
					String propName;

					if (!annotation.name().equals("")) {
						propName = annotation.name();
					} else {
						propName = f.getName();
					}

					f.setAccessible(true);

					try {

						Class type = f.getType();

						if (type == boolean.class) {

							boolean val = config.getBoolean(propName, annotation.category(), f.getBoolean(null), annotation.comment());
							f.setBoolean(null, val);

						} else if (type == int.class) {

							int val = config.getInt(propName, annotation.category(), f.getInt(null), annotation.intMin(), annotation.intMax(), annotation.comment());
							f.setInt(null, val);

						} else if (type == float.class) {

							float val = config.getFloat(propName, annotation.category(), f.getFloat(null), annotation.floatMin(), annotation.floatMax(), annotation.comment());
							f.setFloat(null, val);

						} else if (type == String.class) {

							String val;
							String[] defaults = {"DEFAULT"};

							if (!Arrays.equals(annotation.stringValidValues(), defaults)) { // String with a list of possible values
								val = config.getString(propName, annotation.category(), (String) f.get(null), annotation.comment(), annotation.stringValidValues());
							} else {
								val = config.getString(propName, annotation.category(), (String) f.get(null), annotation.comment());
							}

							f.set(null, val);

						} else if (type == String[].class) {

							String[] val;
							String[] defaults = {"DEFAULT"};

							if (!Arrays.equals(annotation.stringValidValues(), defaults)) {
								val = config.getStringList(propName, annotation.category(), (String[]) f.get(null), annotation.comment(), annotation.stringValidValues());
							} else {
								val = config.getStringList(propName, annotation.category(), (String[]) f.get(null), annotation.comment());
							}

							f.set(null, val);

						}

					} catch (IllegalAccessException e) {
						log.error("Couldn't access one of the config values, skipping.");
						e.printStackTrace();
					}
				}

			}
		}

		config.save();
	}

	public void regenConfig(String name) {
		configs.get(name).getForgeConfig().getConfigFile().delete();
		load(name);
	}

}
