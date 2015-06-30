package net.shadowfacts.shadowmc.config;

import net.minecraftforge.common.config.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A config property.
 * MUST BE STATIC.
 * @author shadowfacts
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigProperty {

	/**
	 * The category for the field, default is "general".
	 * @return The category name
	 */
	public String category() default Configuration.CATEGORY_GENERAL;

	/**
	 * If left as an empty string, the ConfigManager will use the name of the field.
	 * @return The name of the config property.
	 */
	public String name() default "";

	/**
	 * The comment to be displayed in front of the property in the .cfg file.
	 * @return The comment
	 */
	public String comment() default "";

	/**
	 * Only usable for integer properties.
	 * @return The minimum value.
	 */
	public int intMin() default 0;

	/**
	 * Only usable for integer properties.
	 * @return The maximum value.
	 */
	public int intMax() default Integer.MAX_VALUE;

	/**
	 * Only usable for float properties.
	 * @return The minimum value.
	 */
	public float floatMin() default 0f;

	/**
	 * Only usable for float properties.
	 * @return The maximum value.
	 */
	public float floatMax() default Float.MAX_VALUE;


	/**
	 * A list of valid values for string properties.
	 * Only usable for string properties.
	 * @return The String[] of valid values.
	 */
	public String[] stringValidValues() default {"DEFAULT"};
}
