package net.shadowfacts.shadowmc.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for the config system, used on a class to be registered with the ConfigManager.
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {

	/**
	 * Config file name
	 */
	String name();

	/**
	 * Folder to place the config file in, relative to the run directory.
	 */
	String directory() default "config/shadowfacts";

}
