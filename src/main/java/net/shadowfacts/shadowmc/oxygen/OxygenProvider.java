package net.shadowfacts.shadowmc.oxygen;

/**
 * Anything from which oxygen can be extracted
 *
 * You should probably not implement this yourself.
 *
 * @author shadowfacts
 */
public interface OxygenProvider extends OxygenHandler {

	/**
	 * Extract stored oxygen from this handler
	 * @param amount The maximum possible amount to extract
	 * @param simulate If {@code true}, doesn't perform this operation, just returns the result.
	 * @return The actual amount of stored extracted.
	 */
	float extract(float amount, boolean simulate);

}
