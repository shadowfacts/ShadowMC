package net.shadowfacts.shadowmc.compat;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.shadowmc.BaseMod;
import net.shadowfacts.shadowmc.util.LogHelper;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
public class CompatManager {

	private LogHelper log;

	private List<Class> modules = new ArrayList<>();

	public CompatManager(BaseMod owner) {
		log = new LogHelper(owner.getName() + "|Compat");
	}

	public boolean registerModule(Class clazz) {
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

	public void preInit(FMLPreInitializationEvent event) {
		log.info("Attempting to run pre-initialization methods for all registered compatibility modules");
		for (Class clazz : modules) {
			for (Method m : clazz.getDeclaredMethods()) {
				if (Modifier.isStatic(m.getModifiers()) &&
						m.isAnnotationPresent(Compat.PreInit.class)) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the pre-initialization method for %s", e, annotation.value());
					}
				}
			}
		}
	}

	public void init(FMLInitializationEvent event) {
		log.info("Attempting to run initialization methods for all registered compatibility modules");
		for (Class clazz : modules) {
			for (Method m : clazz.getDeclaredMethods()) {
				if (Modifier.isStatic(m.getModifiers()) &&
						m.isAnnotationPresent(Compat.PreInit.class)) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the initialization method for %s", e, annotation.value());
					}
				}
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		log.info("Attempting to run post-initialization methods for all registered compatibility modules");
		for (Class clazz : modules) {
			for (Method m : clazz.getDeclaredMethods()) {
				if (Modifier.isStatic(m.getModifiers()) &&
						m.isAnnotationPresent(Compat.PreInit.class)) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						log.error("There was an error trying to invoke the post-initialization method for %s", e, annotation.value());
					}
				}
			}
		}
	}

}
