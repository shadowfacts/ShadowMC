package net.shadowfacts.shadowcore.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Config for the new config system, used on a class to be registered with the ConfigManager.
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {

	public String name();

	public boolean useSubFolder() default true;

	public String folder() default "shadow";

}
