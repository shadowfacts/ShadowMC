package net.shadowfacts.shadowmc.ui.style.stylesheet;

import net.shadowfacts.shadowmc.ui.style.UIAttribute;

/**
 * @author shadowfacts
 */
public class Rule<T> {

	public final UIAttribute<T> attr;
	public final T value;

	public final boolean important;

	public Rule(String name, String value, boolean important) {
		this.attr = UIAttribute.get(name);
		this.value = UIAttribute.getValueFactory(attr).apply(value);
		this.important = important;
	}

}
