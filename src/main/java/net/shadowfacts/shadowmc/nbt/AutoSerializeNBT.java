package net.shadowfacts.shadowmc.nbt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For fields (de)serializes only the field
 * For types (de)serializes all fields
 *
 * @author shadowfacts
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoSerializeNBT {
}
