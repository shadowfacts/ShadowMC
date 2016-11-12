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
	var id: String = ""
	var classes: MutableList<String> = mutableListOf()

	fun addClass(clazz: String) {
		classes.add(clazz)
	}

	abstract internal fun create(): T
}

class BtnTextContext : CreationContext<UIButtonText>() {
	var text: String = ""
	var handler: ((UIButtonText, MouseButton) -> Boolean)? = null

	override fun create(): UIButtonText {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "cannot have a null handler" }
		return UIButtonText(text, BiFunction { btn, mouseBtn -> handler!!(btn, mouseBtn) }, id, *classes.toTypedArray())
	}
}

class BtnEnumContext<E : Enum<E>> : CreationContext<UIButtonEnum<E>>() {
	var value: E? = null
	var localizer: ((E) -> String)? = null
	var clickHandler: ((UIButtonEnum<E>) -> Unit)? = null

	override fun create(): UIButtonEnum<E> {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(value != null) { "value cannot be null" }
		require(localizer != null) { "localizer cannot be null" }
		return UIButtonEnum<E>(value, Function { localizer!!(it) }, Consumer { if (clickHandler != null) clickHandler!!(it) }, id, *classes.toTypedArray())
	}
}

class BtnLinkContext : CreationContext<UIButtonLink>() {
	var text: String = ""
	var link: URI? = null
	override fun create(): UIButtonLink {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(link != null) { "cannot have a null link" }
		return UIButtonLink(text, link, id, *classes.toTypedArray())
	}

}

class BtnToggleContext : CreationContext<UIButtonToggle>() {
	var state: Boolean = true
	var handler: ((UIButtonToggle) -> Unit)? = null

	override fun create(): UIButtonToggle {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "cannot have a null handler" }
		return UIButtonToggle(state, Consumer { handler!!(it) }, id, *classes.toTypedArray())
	}
}

class BtnDyeColorContext : CreationContext<UIButtonDyeColor>() {
	var color = EnumDyeColor.WHITE
	var handler: ((EnumDyeColor) -> Unit)? = null

	override fun create(): UIButtonDyeColor {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "handler cannot be null" }
		return UIButtonDyeColor(color, Consumer { handler!!(it) }, id, *classes.toTypedArray())
	}
}

class BtnRedstoneModeContext : CreationContext<UIButtonRedstoneMode>() {
	var mode = RedstoneMode.ALWAYS
	var callback: ((RedstoneMode) -> Unit)? = null

	override fun create(): UIButtonRedstoneMode {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(callback != null) { "callback cannot be null" }
		return UIButtonRedstoneMode(mode, Consumer { callback!!(it) }, id, *classes.toTypedArray())
	}
}

class LabelContext : CreationContext<UILabel>() {
	var text: String = ""
	var width: Int? = null

	override fun create(): UILabel {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(!text.isEmpty()) { "text cannot be empty" }
		if (width == null) {
			width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)
		}
		return UILabel(text, width!!, id, *classes.toTypedArray())
	}
}

class ImageContext : CreationContext<UIImage>() {
	var texture: ResourceLocation? = null
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
		require(!id.isEmpty()) { "id cannot be empty" }
		require(texture != null) { "textured cannot be null" }
		return UIImage(texture, u, v, width, height, id, *classes.toTypedArray())
	}
}

class TextFieldContext : CreationContext<UITextField>() {
	var text = ""
	var validator: Pattern? = null
	var handler: ((String) -> Unit)? = null
	var enabled = true
	var preferredWidth = 200

	override fun create(): UITextField {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "handler cannot be null" }
		val textfield = UITextField(text, validator, Consumer { handler!!(it) }, id, *classes.toTypedArray())
//		TODO: Kotlin + lombok in IDEA is broken
//		textfield.setEnabled(enabled)
//		textfield.setPreferredWidth(preferredWidth)
		return textfield
	}
}

class IntFieldContext : CreationContext<UIIntegerField>() {
	var handler: ((Int) -> Unit)? = null
	var value: Int? = null
	var min = Int.MIN_VALUE
	var max = Int.MAX_VALUE

	override fun create(): UIIntegerField {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "handler cannot be null" }
		require(value != null) { "handler cannot be null" }
		return UIIntegerField(IntConsumer { handler!!(it) }, value!!, min, max, id, *classes.toTypedArray())
	}
}

class BarIndicatorContext : CreationContext<UIBarIndicator>() {
	var levelSupplier: (() -> Float)? = null
	var tooltip: (MutableList<String>) -> Unit = {}
	var textured = false

	override fun create(): UIBarIndicator {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(levelSupplier != null) { "levelSupplier cannot be null" }
		if (textured) {
			return UITexturedBarIndicator(Supplier { levelSupplier!!() }, Consumer { tooltip(it) }, id, *classes.toTypedArray())
		} else {
			return UIBarIndicator(Supplier { levelSupplier!!() }, Consumer { tooltip(it) }, id, *classes.toTypedArray())
		}
	}
}


class FluidIndicatorContext : CreationContext<UIFluidIndicator>() {
	var tank: IFluidTank? = null

	override fun create(): UIFluidIndicator {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(tank != null) { "tank cannot be null" }
		return UIFluidIndicator(tank, id, *classes.toTypedArray())
	}
}

class ItemStackContext : CreationContext<UIItemStack>() {
	var stack: ItemStack? = null
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
		require(!id.isEmpty()) { "id cannot be empty" }
		require(stack != null) { "stack cannot be null" }
		return UIItemStack(stack, width, height, id, *classes.toTypedArray())
	}
}

class OxygenIndicatorContext : CreationContext<UIOxygenIndicator>() {
	val handler: OxygenHandler? = null

	override fun create(): UIOxygenIndicator {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(handler != null) { "handler cannot be null" }
		return UIOxygenIndicator(handler!!, id, *classes.toTypedArray())
	}
}

class RectContext : CreationContext<UIRect>() {
	val width: Int? = null
	val height: Int? = null

	override fun create(): UIRect {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(width != null) { "width cannot be null" }
		require(height != null) { "height cannot be null" }
		return UIRect(width!!, height!!, id, *classes.toTypedArray())
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
	var width: Int? = null
	var height: Int? = null

	override fun createView(): UIFixedView {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(width != null) { "width cannot be empty" }
		require(height != null) { "height cannot be empty" }
		return UIFixedView(width!!, height!!, id, *classes.toTypedArray())
	}
}

class StackViewContext : ViewCreationContext<UIStackView>() {
	override fun createView(): UIStackView {
		require(!id.isEmpty()) { "id cannot be empty" }
		return UIStackView(id, *classes.toTypedArray())
	}
}

class ListViewContext : ViewCreationContext<UIListView>() {
	var width: Int? = null
	var height: Int? = null

	override fun createView(): UIListView {
		require(!id.isEmpty()) { "id cannot be empty" }
		require(width != null) { "width cannot be null" }
		require(height != null) { "height cannot be null" }
		return UIListView(width!!, height!!, id, *classes.toTypedArray())
	}
}
