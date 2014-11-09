package net.shadowfacts.shadowcore.util;

import net.shadowfacts.enfusion.EnFusion;

public class CoreUtils {
	
	public static boolean isOpOrServer(String senderName) {
		return EnFusion.proxy.isOp(senderName) || senderName.equals("Server");
	}
	
	public static boolean isClient() {
		return EnFusion.proxy.isClient();
	}
	
	public static boolean isServer() {
		return EnFusion.proxy.isServer();
	}
	
}
