package net.shadowfacts.shadowcore.util;

import net.shadowfacts.shadowcore.ShadowCore;

public class CoreUtils {
	
	public static boolean isOpOrServer(String senderName) {
		return ShadowCore.proxy.isOp(senderName) || senderName.equals("Server");
	}
	
	public static boolean isClient() {
		return ShadowCore.proxy.isClient();
	}
	
	public static boolean isServer() {
		return ShadowCore.proxy.isServer();
	}
	
}
