package net.shadowfacts.shadowmc.ui.element.view;

import lombok.Getter;
import net.minecraft.util.ITickable;
import net.shadowfacts.shadowmc.ui.UIElement;
import net.shadowfacts.shadowmc.ui.UIKeyInteractable;
import net.shadowfacts.shadowmc.ui.UIMouseInteractable;
import net.shadowfacts.shadowmc.ui.element.UIElementBase;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author shadowfacts
 */
public abstract class UIView extends UIElementBase implements UIMouseInteractable, UIKeyInteractable, ITickable {

	@Getter
	protected Set<UIElement> children = new LinkedHashSet<>();

	public UIView(String type, String id, String... classes) {
		super(type, id, classes);
	}

	public void add(UIElement element) {
		element.setParent(this);
		children.add(element);
	}

	public void addAll(Collection<UIElement> elements) {
		for (UIElement element : elements) {
			add(element);
		}
	}

	@Override
	public void setX(int x) {
		for (UIElement e : children) {
			e.setX(e.getX() - this.x + x);
		}
		super.setX(x);
	}

	@Override
	public void setY(int y) {
		for (UIElement e : children) {
			e.setY(e.getY() - this.y + y);
		}
		super.setY(y);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		children.forEach(e -> e.draw(mouseX, mouseY));
	}

	@Override
	public void update() {
		children.stream()
				.filter(e -> e instanceof ITickable)
				.map(e -> (ITickable)e)
				.forEach(ITickable::update);
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {
		children.stream()
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.forEach(e -> e.drawTooltip(mouseX, mouseY));
	}

	@Override
	public void mouseClickDown(int mouseX, int mouseY, MouseButton button) {
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseClickDown(mouseX, mouseY, button));
	}

	@Override
	public void mouseClickUp(int mouseX, int mouseY, MouseButton button) {
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseClickUp(mouseX, mouseY, button));
	}

	@Override
	public void mouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseClickAnywhere(mouseX, mouseY, button));
	}

	@Override
	public void mouseScroll(int mouseX, int mouseY, int delta) {
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseScroll(mouseX, mouseY, delta));
	}

	@Override
	public void mouseMove(int mouseX, int mouseY, MouseButton button, long timeSinceLastClick) {
		children.stream()
				.filter(e -> e instanceof UIMouseInteractable)
				.filter(e -> UIHelper.isWithinBounds(mouseX, mouseY, e))
				.map(e -> (UIMouseInteractable)e)
				.forEach(e -> e.mouseMove(mouseX, mouseY, button, timeSinceLastClick));
	}

	@Override
	public void keyPress(int keyCode, char keyChar) {
		children.stream()
				.filter(e -> e instanceof UIKeyInteractable)
				.map(e -> (UIKeyInteractable)e)
				.forEach(e -> e.keyPress(keyCode, keyChar));
	}

}
