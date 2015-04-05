package net.shadowfacts.shadowcore;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class Log {
//	public static final String CHANNEL = ShadowCore.modId;
	
	public String channel;
	
	public Log(String channel) {
		this.channel = channel;
	}
	
	public void warn(String msg) {
		FMLLog.log(this.channel, Level.WARN, msg);
	}

	public void warn(String msg, Object... args) {
		warn(String.format(msg, args));
	}
	
	public void error(String msg) {
		FMLLog.log(this.channel, Level.ERROR, msg);
	}

	public void error(String msg, Object... args) {
		error(String.format(msg, args));
	}

	public void info(String msg) {
		FMLLog.log(this.channel, Level.INFO, msg);
	}

	public void info(String msg, Object... args){
		info(String.format(msg, args));
	}

	public void debug(String msg) {
		FMLLog.log(this.channel, Level.DEBUG, msg);
	}

	public void debug(String msg, Object... args) {
		debug(String.format(msg, args));
	}
}
