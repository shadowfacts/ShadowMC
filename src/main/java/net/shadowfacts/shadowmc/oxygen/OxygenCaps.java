package net.shadowfacts.shadowmc.oxygen;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * @author shadowfacts
 */
public class OxygenCaps {

	@CapabilityInject(OxygenHandler.class)
	public static Capability<OxygenHandler> HANDLER;

	@CapabilityInject(OxygenProvider.class)
	public static Capability<OxygenProvider> PROVIDER;

	@CapabilityInject(OxygenReceiver.class)
	public static Capability<OxygenReceiver> RECEIVER;

}
