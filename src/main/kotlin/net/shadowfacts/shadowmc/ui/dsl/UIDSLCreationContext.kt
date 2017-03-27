package net.shadowfacts.shadowmc.ui.dsl

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.IFluidTank
import net.shadowfacts.shadowmc.oxygen.OxygenHandler
import net.shadowfacts.shadowmc.ui.UIElement
import net.shadowfacts.shadowmc.ui.element.*
import net.shadowfacts.shadowmc.ui.element.button.*
import net.shadowfacts.shadowmc.ui.element.textfield.UIIntegerField
import net.shadowfacts.shadowmc.ui.element.textfield.UITextField
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView
import net.shadowfacts.shadowmc.ui.element.view.UIListView
import net.shadowfacts.shadowmc.ui.element.view.UIStackView
import net.shadowfacts.shadowmc.ui.element.view.UIView
import net.shadowfacts.shadowmc.util.MouseButton
import net.shadowfacts.shadowmc.util.RedstoneMode
import java.awt.image.BufferedImage
import java.net.URI
import java.util.*
import java.util.function.*
import java.util.function.Function
import java.util.regex.Pattern

/**
 * @author shadowfacts
 */
abstract class CreationContext<out T> {
	lateinit var id: String
	var classes: MutableList<String> = mutableListOf()

	fun addClass(clazz: String) {
		classes.add(clazz)
	}

	abstract internal fun create(): T
}

class BtnTextContext : CreationContext<UIButtonText>() {
	var text: String = ""
	lateinit var handler: ((UIButtonText, MouseButton) -> Boolean)

	override fun create(): UIButtonText {
		return UIButtonText(text, BiFunction { btn, mouseBtn -> handler(btn, mouseBtn) }, id, *classes.toTypedArray())
	}
}

class BtnEnumContext<E : Enum<E>> : CreationContext<UIButtonEnum<E>>() {
	lateinit var value: E
	lateinit var localizer: ((E) -> String)
	lateinit var clickHandler: ((UIButtonEnum<E>) -> Unit)

	override fun create(): UIButtonEnum<E> {
		return UIButtonEnum(value, Function { localizer(it) }, Consumer { clickHandler(it) }, id, *classes.toTypedArray())
	}
}

class BtnLinkContext : CreationContext<UIButtonLink>() {
	var text: String = ""
	lateinit var link: URI

	override fun create(): UIButtonLink {
		return UIButtonLink(text, link, id, *classes.toTypedArray())
	}
}

class BtnToggleContext : CreationContext<UIButtonToggle>() {
	var state: Boolean = true
	lateinit var handler: ((UIButtonToggle) -> Unit)

	override fun create(): UIButtonToggle {
		return UIButtonToggle(state, Consumer { handler(it) }, id, *classes.toTypedArray())
	}
}

class BtnDyeColorContext : CreationContext<UIButtonDyeColor>() {
	var color = EnumDyeColor.WHITE
	lateinit var handler: ((EnumDyeColor) -> Unit)

	override fun create(): UIButtonDyeColor {
		return UIButtonDyeColor(color, Consumer { handler(it) }, id, *classes.toTypedArray())
	}
}

class BtnRedstoneModeContext : CreationContext<UIButtonRedstoneMode>() {
	var mode = RedstoneMode.ALWAYS
	lateinit var callback: ((RedstoneMode) -> Unit)

	override fun create(): UIButtonRedstoneMode {
		require(!id.isEmpty()) { "id cannot be empty" }
		return UIButtonRedstoneMode(mode, Consumer { callback(it) }, id, *classes.toTypedArray())
	}
}

class LabelContext : CreationContext<UILabel>() {
	lateinit var text: String
	var width: Int? = null

	override fun create(): UILabel {
		return UILabel(text, width ?: Minecraft.getMinecraft().fontRenderer.getStringWidth(text), id, *classes.toTypedArray())
	}
}

class ImageContext : CreationContext<UIImage>() {
	lateinit var texture: ResourceLocation
	var u = 0
	var v = 0
	var width = 0
	var height = 0

	fun dynamic(id: String, image: BufferedImage) {
		texture = Minecraft.getMinecraft().textureManager.getDynamicTextureLocation(id, DynamicTexture(image))
		u = 0
		v = 0
		width = image.width
		height = image.height
	}

	override fun create(): UIImage {
		return UIImage(texture, u, v, width, height, id, *classes.toTypedArray())
	}
}

class TextFieldContext : CreationContext<UITextField>() {
	var text = ""
	var validator: Pattern = Pattern.compile(".+")
	lateinit var handler: ((String) -> Unit)
	var enabled = true
	var preferredWidth = 200

	override fun create(): UITextField {
		val textfield = UITextField(text, validator, Consumer { handler(it) }, id, *classes.toTypedArray())
//		TODO: Kotlin + lombok in IDEA is broken
//		textfield.setEnabled(enabled)
//		textfield.setPreferredWidth(preferredWidth)
		return textfield
	}
}

class IntFieldContext : CreationContext<UIIntegerField>() {
	lateinit var handler: ((Int) -> Unit)
	var value: Int = 0
	var min = Int.MIN_VALUE
	var max = Int.MAX_VALUE

	override fun create(): UIIntegerField {
		return UIIntegerField(IntConsumer { handler(it) }, value, min, max, id, *classes.toTypedArray())
	}
}

class BarIndicatorContext : CreationContext<UIBarIndicator>() {
	lateinit var levelSupplier: (() -> Float)
	var tooltip: (MutableList<String>) -> Unit = {}
	var textured = false

	override fun create(): UIBarIndicator {
		if (textured) {
			return UITexturedBarIndicator(Supplier { levelSupplier() }, Consumer { tooltip(it) }, id, *classes.toTypedArray())
		} else {
			return UIBarIndicator(Supplier { levelSupplier() }, Consumer { tooltip(it) }, id, *classes.toTypedArray())
		}
	}
}


class FluidIndicatorContext : CreationContext<UIFluidIndicator>() {
	lateinit var tank: IFluidTank

	override fun create(): UIFluidIndicator {
		return UIFluidIndicator(tank, id, *classes.toTypedArray())
	}
}

class ItemStackContext : CreationContext<UIItemStack>() {
	lateinit var stack: ItemStack
	var width = 16
	var height = 16
	var tooltip = false

	fun setStack(item: Item) {
		stack = ItemStack(item)
	}

	fun setStack(block: Block) {
		stack = ItemStack(block)
	}

	override fun create(): UIItemStack {
		return UIItemStack(stack, width, height, id, *classes.toTypedArray())
	}
}

class OxygenIndicatorContext : CreationContext<UIOxygenIndicator>() {
	lateinit var handler: OxygenHandler

	override fun create(): UIOxygenIndicator {
		return UIOxygenIndicator(handler, id, *classes.toTypedArray())
	}
}

class RectContext : CreationContext<UIRect>() {
	var width: Int = 0
	var height: Int = 0

	override fun create(): UIRect {
		return UIRect(width, height, id, *classes.toTypedArray())
	}
}

abstract class ViewCreationContext<out T : UIView> : CreationContext<T>(), UIChildrenContext {
	private var children: LinkedHashSet<UIElement> = LinkedHashSet()

	override fun <T : UIElement> add(element: T): T {
		children.add(element)
		return element
	}

	override fun create(): T {
		val view = createView()
		view.addAll(children)
		return view
	}

	protected abstract fun createView(): T
}

class FixedViewContext : ViewCreationContext<UIFixedView>() {
	var width: Int = 0
	var height: Int = 0

	override fun createView(): UIFixedView {
		return UIFixedView(width, height, id, *classes.toTypedArray())
	}
}

class StackViewContext : ViewCreationContext<UIStackView>() {
	override fun createView(): UIStackView {
		require(!id.isEmpty()) { "id cannot be empty" }
		return UIStackView(id, *classes.toTypedArray())
	}
}

class ListViewContext : ViewCreationContext<UIListView>() {
	var width: Int = 0
	var height: Int = 0

	override fun createView(): UIListView {
		return UIListView(width, height, id, *classes.toTypedArray())
	}
}
