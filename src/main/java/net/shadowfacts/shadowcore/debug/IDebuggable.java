package net.shadowfacts.shadowcore.debug;

import java.util.ArrayList;

/**
 * An interface that should be used for any blocks/tile entities that can be debugged.
 * @author shadowfacts
 */
public interface IDebuggable {
	public ArrayList<String> getDebugInfo();
}
