package net.shadowfacts.shadowmc.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.BiFunction;

/**
 * @author shadowfacts
 */
public class CapHelper {

	public static boolean hasCapability(Capability<?> capability, EnumFacing facing, Object object) {
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers()) && f.isAnnotationPresent(CapHolder.class)) {
				CapHolder holder = f.getAnnotation(CapHolder.class);
				if (contains(holder.capabilities(), capability.getName()) && contains(holder.sides(), facing)) {
					return true;
				}
			}
		}

		return false;
	}

	public static <T> T getCapability(Capability<T> capability, EnumFacing facing, Object object, BiFunction<Capability, EnumFacing, T> defaultFunc) {
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers()) && f.isAnnotationPresent(CapHolder.class)) {
				CapHolder holder = f.getAnnotation(CapHolder.class);
				if (contains(holder.capabilities(), capability.getName()) && contains(holder.sides(), facing)) {
					try {
						f.setAccessible(true);
						return (T) f.get(object);
					} catch (ReflectiveOperationException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return defaultFunc.apply(capability, facing);
	}

	private static <T> boolean contains(T[] array, T value) {
		for (T t : array) {
			if (t.equals(value)) {
				return true;
			}
		}
		return false;
	}

}
