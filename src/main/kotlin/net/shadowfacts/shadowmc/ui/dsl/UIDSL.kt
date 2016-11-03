package net.shadowfacts.shadowmc.ui.dsl

import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.Container
import net.shadowfacts.shadowmc.ui.UIElement
import net.shadowfacts.shadowmc.ui.element.*
import net.shadowfacts.shadowmc.ui.element.button.*
import net.shadowfacts.shadowmc.ui.element.textfield.UIIntegerField
import net.shadowfacts.shadowmc.ui.element.textfield.UITextField
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView
import net.shadowfacts.shadowmc.ui.element.view.UIListView
import net.shadowfacts.shadowmc.ui.element.view.UIStackView
import net.shadowfacts.shadowmc.ui.util.UIBuilder

/**
 * @author shadowfacts
 */
fun screen(init: BuilderContext.() -> Unit): GuiScreen {
	val builder = UIBuilder()
	val context = BuilderContext(builder)
	context.init()
	return builder.createScreen()
}

fun container(container: Container, init: BuilderContext.() -> Unit): GuiContainer {
	val builder = UIBuilder()
	val context = BuilderContext(builder)
	context.init()
	return builder.createContainer(container)
}

interface UIChildrenContext {
	fun <T : UIElement> add(element: T): T

	// Button
	fun buttonText(init: BtnTextContext.() -> Unit): UIButtonText {
		val context = BtnTextContext()
		context.init()
		return add(context.create())
	}

	fun <E : Enum<E>> buttonEnum(clazz: Class<E>, init: BtnEnumContext<E>.() -> Unit): UIButtonEnum<E> {
		val context = BtnEnumContext<E>()
		context.init()
		return add(context.create())
	}

	fun buttonLink(init: BtnLinkContext.() -> Unit): UIButtonLink {
		val context = BtnLinkContext()
		context.init()
		return add(context.create())
	}

	fun buttonToggle(init: BtnToggleContext.() -> Unit): UIButtonToggle {
		val context = BtnToggleContext()
		context.init()
		return add(context.create())
	}

	fun buttonDyeColor(init: BtnDyeColorContext.() -> Unit): UIButtonDyeColor {
		val context = BtnDyeColorContext()
		context.init()
		return add(context.create())
	}

	fun buttonRedstoneMode(init: BtnRedstoneModeContext.() -> Unit): UIButtonRedstoneMode {
		val context = BtnRedstoneModeContext()
		context.init()
		return add(context.create())
	}

	// Text field
	fun textField(init: TextFieldContext.() -> Unit): UITextField {
		val context = TextFieldContext()
		context.init()
		return add(context.create())
	}

	fun intField(init: IntFieldContext.() -> Unit): UIIntegerField {
		val context = IntFieldContext()
		context.init()
		return add(context.create())
	}

	// View
	fun fixed(init: FixedViewContext.() -> Unit): UIFixedView {
		val context = FixedViewContext()
		context.init()
		return add(context.create())
	}

	fun stack(init: StackViewContext.() -> Unit): UIStackView {
		val context = StackViewContext()
		context.init()
		return add(context.create())
	}

	fun list(init: ListViewContext.() -> Unit): UIListView {
		val context = ListViewContext()
		context.init()
		return add(context.create())
	}

	// Other
	fun label(init: LabelContext.() -> Unit): UILabel {
		val context = LabelContext()
		context.init()
		return add(context.create())
	}

	fun image(init: ImageContext.() -> Unit): UIImage {
		val context = ImageContext()
		context.init()
		return add(context.create())
	}

	fun barIndicator(init: BarIndicatorContext.() -> Unit): UIBarIndicator {
		val context = BarIndicatorContext()
		context.init()
		return add(context.create())
	}

	fun fluidIndicator(init: FluidIndicatorContext.() -> Unit): UIFluidIndicator {
		val context = FluidIndicatorContext()
		context.init()
		return add(context.create())
	}

	fun oxygenIndicator(init: OxygenIndicatorContext.() -> Unit): UIOxygenIndicator {
		val context = OxygenIndicatorContext()
		context.init()
		return add(context.create())
	}

	fun itemStack(init: ItemStackContext.() -> Unit): UIItemStack {
		val context = ItemStackContext()
		context.init()
		return add(context.create())
	}

	fun rect(init: RectContext.() -> Unit): UIRect {
		val context = RectContext()
		context.init()
		return add(context.create())
	}

}

class BuilderContext(val builder: UIBuilder) : UIChildrenContext {
	override fun <T : UIElement> add(element: T): T {
		builder.add(element)
		return element
	}

	fun style(id: String) {
		builder.style(id)
	}

	fun clearKeyHandlers() {
		builder.clearKeyHandlers()
	}

	fun keyHandler(key: Int, handler: () -> Unit) {
		builder.addKeyHandler { keyCode, keyChar ->
			if (keyCode == key) {
				handler()
			}
		}
	}

	fun updateHandler(handler: () -> Unit) {
		builder.setUpdateHandler(handler)
	}

	fun closeHandler(handler: () -> Unit) {
		builder.setCloseHandler(handler)
	}

	fun pausesGame(pausesGame: Boolean) {
		builder.setPausesGame(pausesGame)
	}
}
