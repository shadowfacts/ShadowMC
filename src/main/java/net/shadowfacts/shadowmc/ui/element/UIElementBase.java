package net.shadowfacts.shadowmc.ui.element;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.style.*;
import net.shadowfacts.shadowmc.ui.style.stylesheet.Rule;

import java.util.*;

/**
 * @author shadowfacts
 */
public abstract class UIElementBase implements UIElement {

	protected final Minecraft mc = Minecraft.getMinecraft();

	private final Map<UIAttribute, Object> style = new HashMap<>();

	@Getter @Setter
	protected int x;
	@Getter @Setter
	protected int y;
	@Getter @Setter
	protected UIDimensions dimensions;

	@Getter @Setter
	private UIElement parent;

	@Setter
	private Runnable invalidationHandler = () -> parent.invalidateLayout();

	@Getter
	private final String type;

	private String id;

	@Getter @Setter
	private Set<String> classes = new LinkedHashSet<>();

	public UIElementBase(String type, String id, String... classes) {
		this.type = type;
		this.id = id;
		Collections.addAll(this.classes, classes);
	}

	@Override
	public abstract UIDimensions getMinDimensions();

	@Override
	public UIDimensions getMaxDimensions() {
		return UIDimensions.max();
	}

	@Override
	public abstract UIDimensions getPreferredDimensions();

	@Override
	public void invalidateLayout() {
		invalidationHandler.run();
	}

	@Override
	public void layout(int minX, int maxX, int minY, int maxY) {
		UILayoutMode layoutMode = getStyle(UIAttribute.LAYOUT);
		switch (layoutMode) {
			case MIN:
				dimensions = getMinDimensions();
				break;
			default:
			case PREFERRED:
				dimensions = getPreferredDimensions();
				break;
			case MAX:
				dimensions = getMaxDimensions();
				break;
		}

		UIHorizontalLayoutMode horizontalLayoutMode = getStyle(UIAttribute.HORIZONTAL_LAYOUT);
		switch (horizontalLayoutMode) {
			case LEFT:
				x = minX;
				break;
			default:
			case CENTER:
				x = (maxX / 2) - (dimensions.width / 2);
				break;
			case RIGHT:
				x = maxX - dimensions.width;
				break;
		}

		UIVerticalLayoutMode verticalLayoutMode = getStyle(UIAttribute.VERTICAL_LAYOUT);
		switch (verticalLayoutMode) {
			case TOP:
				y = minY;
				break;
			default:
			case CENTER:
				y = (maxY / 2) - (dimensions.height / 2);
				break;
			case BOTTOM:
				y = maxY - dimensions.height;
				break;
		}
	}

	@Override
	public abstract void draw(int mouseX, int mouseY);

	@Override
	public <T> void setStyle(UIAttribute<T> attr, T val) {
		style.put(attr, val);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getStyle(UIAttribute<T> attr) {
		T val = (T)style.get(attr);
		return val != null ? val : attr.getDefaultVal();
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setID(String id) {
		this.id = id;
	}

	@Override
	public void addClass(String clazz) {
		classes.add(clazz);
	}

	@Override
	public void applyRule(Rule<?> rule) {
		if (!style.containsKey(rule.attr) || rule.important) {
			style.put(rule.attr, rule.value);
		}
	}

}
