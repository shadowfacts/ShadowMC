package net.shadowfacts.shadowmc.util;

import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
public class LogHelper {

	private String name;
	private Logger logger;

	public LogHelper(String name) {
		this.name = name;
		logger = LogManager.getLogger(name);
	}

	public void info(String s) {
		logger.info(getMessage(s));
	}

	public void info(String s, Object... params) {
		info(String.format(getMessage(s), params));
	}

	public void warn(String s) {
		logger.warn(getMessage(s));
	}

	public void warn(String s, Object... params) {
		warn(String.format(getMessage(s), params));
	}

	public void error(String s) {
		logger.error(getMessage(s));
	}

	public void error(String s, Object... params) {
		error(String.format(getMessage(s), params));
	}

	public void error(String s, Throwable t) {
		error(getMessage(s));
		t.printStackTrace();
	}

	public void error(String s, Throwable t, Object... params) {
		error(getMessage(s), params);
		t.printStackTrace();
	}

	private String getMessage(String msg) {
		return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment") ? "[" + name + "] " + msg : msg;
	}

}
