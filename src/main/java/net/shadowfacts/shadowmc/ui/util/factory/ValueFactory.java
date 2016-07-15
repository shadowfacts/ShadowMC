package net.shadowfacts.shadowmc.ui.util.factory;

/**
 * @author shadowfacts
 */
@FunctionalInterface
public interface ValueFactory<T> {

	T create(String s, T defaultVal);

}
