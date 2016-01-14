package net.shadowfacts.shadowmc.compat;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.shadowmc.util.LogHelper;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
public class CompatRegistrar {

	private static LogHelper log = new LogHelper("ShadowMC|Comapt");

	private static List<Class> modules = new ArrayList<>();

	public static boolean registerModule(Class clazz) {
		if (clazz.isAnnotationPresent(Compat.class)) {
			Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
			if (Loader.isModLoaded(annotation.value())) {
				modules.add(clazz);
				return true;
			} else {
				log.info("The mod %s was not loaded, skipping compatibility module", annotation.value());
				return false;
			}
		} else {
			log.error("Cannot register compatibility class %s missing @Compat annotation", clazz.getName());
			return false;
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		log.info("Attempting to run pre-initialization methods for all registered compatibility modules");
		modules.stream()
				.flatMap(clazz -> Arrays.stream(clazz.getMethods()))
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.filter(method -> method.isAnnotationPresent(Compat.PreInit.class))
				.forEach(method -> {
					try {
						method.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = method.getDeclaringClass().getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the pre-initialization method for %s", e, annotation.value());
					}
				});
	}

	public static void init(FMLInitializationEvent event) {
		log.info("Attempting to run pre-initialization methods for all registered compatibility modules");
		modules.stream()
				.flatMap(clazz -> Arrays.stream(clazz.getMethods()))
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.filter(method -> method.isAnnotationPresent(Compat.Init.class))
				.forEach(method -> {
					try {
						method.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = method.getDeclaringClass().getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the initialization method for %s", e, annotation.value());
					}
				});
	}

	public static void postInit(FMLPostInitializationEvent event) {
		log.info("Attempting to run pre-initialization methods for all registered compatibility modules");
		modules.stream()
				.flatMap(clazz -> Arrays.stream(clazz.getMethods()))
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.filter(method -> method.isAnnotationPresent(Compat.PostInit.class))
				.forEach(method -> {
					try {
						method.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = method.getDeclaringClass().getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the post-initialization method for %s", e, annotation.value());
					}
				});
	}

}
