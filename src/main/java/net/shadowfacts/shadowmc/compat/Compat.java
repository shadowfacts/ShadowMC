package net.shadowfacts.shadowmc.compat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Annotation used to mark a class as a compatibility module.
 *
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Compat {

	/**
	 * The mod id of the mod that this compat module is for
	 */
	String value();

	/**
	 * Used to mark a method of a compatibility module to be run in the pre-initialization stage
	 * Any method marked with this must have 1 argument, a {@link FMLPreInitializationEvent}
	 * Any method marked with this must be static
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@interface PreInit {

	}

	/**
	 * Used to mark a method of a compatibility module to be run in the initialization phase
	 * Any method marked with this must have 1 argument, a {@link FMLInitializationEvent}
	 * Any method marked with this must be static
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@interface Init {

	}

	/**
	 * Used to mark a method of a compatibility module to be run in the post-initialization phase
	 * Any method marked with this must have 1 argument, a {@link FMLPostInitializationEvent}
	 * Any method marked with this must be static
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@interface PostInit {

	}

}
