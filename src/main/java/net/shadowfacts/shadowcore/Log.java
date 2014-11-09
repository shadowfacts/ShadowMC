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
	
	public void error(String msg) {
		FMLLog.log(this.channel, Level.ERROR, msg);
	}
	
	public void info(String msg) {
		FMLLog.log(this.channel, Level.INFO, msg);
	}
	
	public void debug(String msg) {
		FMLLog.log(this.channel, Level.DEBUG, msg);
	}
}
