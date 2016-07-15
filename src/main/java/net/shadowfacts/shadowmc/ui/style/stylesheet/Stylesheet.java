package net.shadowfacts.shadowmc.ui.style.stylesheet;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.element.view.UIView;

import java.util.Collection;

/**
 * @author shadowfacts
 */
public class Stylesheet {

	private Multimap<String, Rule<?>> typeRules = HashMultimap.create();
	private Multimap<String, Rule<?>> idRules = HashMultimap.create();
	private Multimap<String, Rule<?>> classRules = HashMultimap.create();

	public void addTypeRule(String type, Rule<?> rule) {
		typeRules.put(type, rule);
	}

	public void addIDRule(String id, Rule<?> rule) {
		idRules.put(id, rule);
	}

	public void addClassRule(String clazz, Rule<?> rule) {
		classRules.put(clazz, rule);
	}

	public void style(UIElement e) {
		typeRules.get(e.getType()).forEach(e::applyRule);

		idRules.get(e.getID()).forEach(e::applyRule);

		e.getClasses().stream()
				.map(classRules::get)
				.flatMap(Collection::stream)
				.forEach(e::applyRule);

		if (e instanceof UIView) {
			((UIView)e).getChildren().forEach(this::style);
		}
	}

}
