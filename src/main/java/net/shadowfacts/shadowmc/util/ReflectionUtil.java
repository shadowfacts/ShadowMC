package net.shadowfacts.shadowmc.util;

import lombok.AllArgsConstructor;
import net.minecraft.launchwrapper.Launch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
public class ReflectionUtil {

	private static Map<FieldDesc, Field> fieldMap = new HashMap<>();
	private static Map<MethodDesc, Method> methodMap = new HashMap<>();

	private static Map<String, String> obfFieldMap = new HashMap<>();
	private static Map<String, String> obfMethodMap = new HashMap<>();

	private ReflectionUtil() {}

	/**
	 * Adds a mapping to the internal deobf -> obf field name map
	 * @param deobf The deobfuscated name
	 * @param obf The obfuscated name
	 */
	public static void addObfFieldEntry(String deobf, String obf) {
		obfFieldMap.put(deobf, obf);
	}

	/**
	 * Adds a mapping to the internal deobf -> obf method name map
	 * @param deobf The deobfuscated name
	 * @param obf The obfuscated name
	 */
	public static void addObfMethodEntry(String deobf, String obf) {
		obfMethodMap.put(deobf, obf);
	}

	/**
	 *	Caches a field
	 * @param owner Owner class name
	 * @param name (Deobfuscated) Field name
	 * @throws ClassNotFoundException if the owner class doesn't exist
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	private static void putField(String owner, String name) throws ClassNotFoundException, NoSuchFieldException {
		putField(Class.forName(owner), name);
	}

	/**
	 * Caches a field
	 * @param owner Owner class
	 * @param name (Deobfuscated) field name
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	private static void putField(Class owner, String name) throws NoSuchFieldException {
		Field f = owner.getDeclaredField(getFieldName(name));
		f.setAccessible(true);
		fieldMap.put(new FieldDesc(owner, name), f);
	}

	/**
	 * Caches a method
	 * @param owner Owner class name
	 * @param name (Deobfuscated) method name
	 * @param paramTypes The types of parameters the method takes
	 * @throws ClassNotFoundException if the owner class doesn't exist
	 * @throws NoSuchMethodException if the method doesn't exist
	 */
	private static void putMethod(String owner, String name, Class... paramTypes) throws ClassNotFoundException, NoSuchMethodException {
		putMethod(Class.forName(owner), name, paramTypes);
	}

	/**
	 * Caches a method
	 * @param owner Owner class
	 * @param name (Deobfuscated) method name
	 * @param paramTypes The types of parameters the method takes
	 * @throws NoSuchMethodException if the method doesn't exist
	 */
	private static void putMethod(Class owner, String name, Class... paramTypes) throws NoSuchMethodException {
		Method m = owner.getDeclaredMethod(name, paramTypes);
		m.setAccessible(true);
		methodMap.put(new MethodDesc(owner, name, paramTypes), m);
	}

	/**
	 * Retrieves the popper field name from the deobfuscated name.
	 * This is the same, deobfuscated name if running in a development environment
	 * This is the obfuscated name if running in an obfuscated environment
	 * @param deobfName The deobfuscated name
	 * @return The correct name for the current environment
	 */
	private static String getFieldName(String deobfName) {
		if ((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")) return deobfName;
		return obfFieldMap.get(deobfName);
	}

	/**
	 * Retrieves the proper method name from the deobfuscated name
	 * This is the same, deobfuscated name if running in a development environment
	 * This is the obfuscated name if running in an obfuscated environment
	 * @param deobfName The deobfuscated name
	 * @return The correct name for the current environment
	 */
	private static String getMethodName(String deobfName) {
		if ((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")) return deobfName;
		return obfMethodMap.get(deobfName);
	}

	/**
	 * Retrieves the {@code Object} value of a field
	 * Adds the field to the cache if not already present
	 * @param owner The owner class name
	 * @param name (Deobfuscated) field name
	 * @param instance The instance of the class, {@code null} if static
	 * @return The value of the field
	 * @throws ClassNotFoundException if the owner class doesn't exist
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	public static Object get(String owner, String name, Object instance) throws ClassNotFoundException, NoSuchFieldException {
		return get(Class.forName(owner), name, instance);
	}

	/**
	 * Retrieves the {@code Object} value of a field
	 * Adds the field to the cache if not already present
	 * @param owner The owner class
	 * @param name (Deobfuscated) field name
	 * @param instance The instance of the class, {@code null} if static
	 * @return The value of the field
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	public static Object get(Class owner, String name, Object instance) throws NoSuchFieldException {
		FieldDesc desc = new FieldDesc(owner, name);
		if (!fieldMap.containsKey(desc)) {
			putField(owner, name);
		}
		try {
			return fieldMap.get(desc).get(instance);
		} catch (IllegalAccessException ignored) {} // never happens, we allow access
		return null;
	}

	/**
	 * Retrieves the {@code int} value of a field
	 * Adds the field to the cache if not already present
	 * @param owner The owner class name
	 * @param name (Deobfuscated) field name
	 * @param instance The instance of the class, {@code null} if static
	 * @return The value of the field
	 * @throws ClassNotFoundException if the owner class doesn't exist
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	public static int getInt(String owner, String name, Object instance) throws ClassNotFoundException, NoSuchFieldException {
		return getInt(Class.forName(owner), name, instance);
	}

	/**
	 * Retrieves the {@code int} value of a field
	 * Adds the field to the cache if not already present
	 * @param owner The owner class name
	 * @param name (Deobfuscated) field name
	 * @param instance The instance of the class, {@code null} if static
	 * @return The value of the field
	 * @throws NoSuchFieldException if the field doesn't exist
	 */
	public static int getInt(Class owner, String name, Object instance) throws NoSuchFieldException {
		FieldDesc desc = new FieldDesc(owner, name);
		if (!fieldMap.containsKey(desc)) {
			putField(owner, name);
		}
		try {
			return fieldMap.get(desc).getInt(instance);
		} catch (IllegalAccessException ignored) {} // never happens, we allow access
		return Integer.MIN_VALUE;
	}

	/**
	 * Invokes a method
	 * Adds the method to the cache if not already present
	 * @param owner The owner class name
	 * @param name (Deobfuscated) method name
	 * @param instance The instance of the class, {@code null} if static
	 * @param paramTypes The types of parameters the method accepts
	 * @param args The arguments to pass to the method
	 * @return The return value of the invoked method
	 * @throws ClassNotFoundException if the owner class doesn't exist
	 * @throws NoSuchMethodException if the method doesn't exist
	 * @throws InvocationTargetException if the method cannot be invoked on the instance
	 */
	public static Object invoke(String owner, String name, Object instance, Class[] paramTypes, Object... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		return invoke(Class.forName(owner), name, instance, paramTypes, args);
	}

	/**
	 * Invokes a method
	 * Adds the method to the cache if not already present
	 * @param owner The owner class
	 * @param name (Deobfuscated) method name
	 * @param instance The instance of the class, {@code null} if static
	 * @param paramTypes The types of parameters the method accepts
	 * @param args The arguments to pass to the method
	 * @return The return value of the invoked method
	 * @throws NoSuchMethodException if the method doesn't exist
	 * @throws InvocationTargetException if the method cannot be invoked on the instance
	 */
	public static Object invoke(Class owner, String name, Object instance, Class[] paramTypes, Object... args) throws NoSuchMethodException, InvocationTargetException {
		MethodDesc desc = new MethodDesc(owner, name, paramTypes);
		if (!methodMap.containsKey(desc)) {
			putMethod(owner, name, paramTypes);
		}
		try {
			return methodMap.get(desc).invoke(args);
		} catch (IllegalAccessException ignored) {} // never happens, we allow access
		return null;
	}


	/**
	 * Internal class used for combining field owner/name into 1 thing
	 */
	@AllArgsConstructor
	private static class FieldDesc {
		private Class owner;
		private String name;

		@Override
		public String toString() {
			return owner.getName() + "." + name;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof FieldDesc) {
				FieldDesc other = (FieldDesc)obj;
				return other.owner.equals(owner) && other.name.equals(name);
			}
			return false;
		}
	}

	/**
	 * Internal class used for combining method owner/name/param types into 1 thing
	 */
	@AllArgsConstructor
	private static class MethodDesc {
		private Class owner;
		private String name;
		private Class[] paramTypes;

		@Override
		public String toString() {
			return owner.getName() + "." + name + "(" + Arrays.toString(paramTypes) + ")";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof MethodDesc) {
				MethodDesc other = (MethodDesc)obj;
				return other.owner.equals(owner) && other.name.equals(name) && Arrays.equals(other.paramTypes, paramTypes);
			}
			return false;
		}
	}

}
