package net.shadowfacts.shadowmc.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author shadowfacts
 */
public class CapHelper {

	private static final Set<Class<?>> searched = new HashSet<>();
	private static final Map<String, Map<EnumFacing, Map<Class<?>, Field>>> caps = new HashMap<>();

	public static boolean hasCapability(Capability<?> capability, EnumFacing facing, Class<?> clazz, Object object) {
		if (!searched.contains(clazz)) {
			search(clazz);
		}
		if (caps.containsKey(capability.getName())) {
			Map<EnumFacing, Map<Class<?>, Field>> map = caps.get(capability.getName());
			if (map.containsKey(facing)) {
				Map<Class<?>, Field> map2 = map.get(facing);
				return map2.containsKey(clazz);
			}
		}
		return false;
	}

	public static <T> T getCapability(Capability<T> capability, EnumFacing facing, Class<?> clazz, Object object, BiFunction<Capability, EnumFacing, T> defaultFunc) {
		if (!searched.contains(clazz)) {
			search(clazz);
		}

		try {
			Field f = caps.get(capability.getName()).get(facing).get(clazz);
			f.setAccessible(true);
			return (T)f.get(object);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}

		return defaultFunc.apply(capability, facing);
	}

	private static void search(Class<?> clazz) {
		for (Field f : clazz.getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers()) && f.isAnnotationPresent(CapHolder.class)) {
				CapHolder holder = f.getAnnotation(CapHolder.class);
				for (Class<?> capClass : holder.capabilities()) {

					String name = capClass.getName();

					if (!caps.containsKey(name)) {
						caps.put(name, new HashMap<>());
					}

					for (EnumFacing facing : holder.sides()) {

						if (!caps.get(name).containsKey(facing)) {
							caps.get(name).put(facing, new HashMap<>());
						}

						caps.get(name).get(facing).put(clazz, f);
					}
				}
			}
		}
		searched.add(clazz);
	}

}
