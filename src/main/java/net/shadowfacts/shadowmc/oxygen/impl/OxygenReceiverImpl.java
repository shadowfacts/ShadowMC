package net.shadowfacts.shadowmc.oxygen.impl;

import net.shadowfacts.shadowmc.oxygen.OxygenReceiver;

/**
 * Default oxygen receiver implementation.
 *
 * @author shadowfacts
 */
public class OxygenReceiverImpl extends OxygenHandlerImpl implements OxygenReceiver {

	/**
	 * Default no-args constructor, used by Forge.
	 */
	public OxygenReceiverImpl() {
		super();
	}

	/**
	 * @param capacity The maximum amount of oxygen that can be stored
	 * @param transferRate The maximum amount of oxygen that can be transferred in 1 operation
	 */
	public OxygenReceiverImpl(int capacity, int transferRate) {
		super(capacity, transferRate);
	}

	@Override
	public int receive(int amount, boolean simulate) {
		int received = Math.min(capacity - stored, Math.min(transferRate, amount));

		if (!simulate) stored += received;

		return received;
	}

}
