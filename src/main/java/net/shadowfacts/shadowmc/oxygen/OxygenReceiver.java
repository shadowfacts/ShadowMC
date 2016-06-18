package net.shadowfacts.shadowmc.oxygen;

/**
 * Anything that can receive oxygen.
 *
 * You should probably not implement this yourself.
 *
 * @author shadowfacts
 */
public interface OxygenReceiver extends OxygenHandler {

	/**
	 * Receive oxygen
	 * @param amount The maximum amount of stored to receive
	 * @param simulate If {@code true}, doesn't perform this operation, just returns the result.
	 * @return The actual amount of stored received.
	 */
	float receive(float amount, boolean simulate);

}
