package net.shadowfacts.shadowmc.ui;

import net.shadowfacts.shadowmc.ui.style.stylesheet.Rule;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;

import java.util.Set;

/**
 * @author shadowfacts
 */
public interface UIElement {

	UIDimensions getMinDimensions();
	UIDimensions getMaxDimensions();
	UIDimensions getPreferredDimensions();

	int getX();
	int getY();

	void setX(int x);
	void setY(int y);

	UIDimensions getDimensions();
	void setDimensions(UIDimensions dimensions);

	UIElement getParent();
	void setParent(UIElement parent);

	void setInvalidationHandler(Runnable invalidationHandler);

	void invalidateLayout();

	void layout(int minX, int maxX, int minY, int maxY);

	void draw(int mouseX, int mouseY);

	void drawTooltip(int mouseX, int mouseY);

	<T> void setStyle(UIAttribute<T> attr, T val);

	<T> T getStyle(UIAttribute<T> attr);

	String getType();

	String getID();
	void setID(String id);

	Set<String> getClasses();
	void addClass(String clazz);
	void setClasses(Set<String> classes);

	void applyRule(Rule<?> rule);

}
