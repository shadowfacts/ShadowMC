package net.shadowfacts.shadowmc.capability;

import net.minecraft.util.EnumFacing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CapHolder {

	/**
	 * @return Names of all capabilities this field should be used for
	 */
	String[] capabilities();


	/**
	 * @return The sides this capability is usable for
	 */
	EnumFacing[] sides() default {EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST};

}
