package net.shadowfacts.shadowmc.structure.creator;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.shadowfacts.shadowlib.util.DesktopUtils;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.inventory.ContainerPlayerInv;
import net.shadowfacts.shadowmc.structure.Structure;
import net.shadowfacts.shadowmc.structure.StructureManager;
import net.shadowfacts.shadowmc.ui.element.UILabel;
import net.shadowfacts.shadowmc.ui.element.UIRect;
import net.shadowfacts.shadowmc.ui.element.button.UIButtonText;
import net.shadowfacts.shadowmc.ui.element.button.UIImage;
import net.shadowfacts.shadowmc.ui.element.textfield.UITextField;
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView;
import net.shadowfacts.shadowmc.ui.element.view.UIStackView;
import net.shadowfacts.shadowmc.ui.util.UIBuilder;
import net.shadowfacts.shadowmc.util.KeyboardHelper;

import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
public class GUIStructureCreator {

	public static GuiScreen create(BlockPos pos, InventoryPlayer inventory, TileEntityStructureCreator te) {
		UIFixedView root = new UIFixedView(176, 166, "root");

		UIImage bg = new UIImage(new ResourceLocation(ShadowMC.modId, "textures/gui/blank.png"),  0, 0, 176, 166, "bg");
		root.add(bg);

		UIFixedView top = new UIFixedView(176, 166 / 2, "top");

		UIStackView horizontalStack = new UIStackView("horizontalStack");

//		X
		UIStackView xStack = new UIStackView("zStack");
		UILabel xLabel = new UILabel(Integer.toString(te.xSize), "zLabel");
		UIButtonText xMinus = new UIButtonText("-", (btn, mouseBtn) -> {
			te.xSize -= KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			xLabel.setText(Integer.toString(te.xSize));
			return true;
		}, "zMinus");
		UIButtonText xPlus = new UIButtonText("+", (btn, mouseBtn) -> {
			te.xSize += KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			xLabel.setText(Integer.toString(te.xSize));
			return true;
		}, "yPlus");
		xStack.add(xMinus);
		xStack.add(xLabel);
		xStack.add(xPlus);
		horizontalStack.add(xStack);

		horizontalStack.add(new UILabel("x", "label1"));

//		Y
		UIStackView yStack = new UIStackView("yStack");
		UILabel yLabel = new UILabel(Integer.toString(te.ySize), "yLabel");
		UIButtonText yMinus = new UIButtonText("-", (btn, mouseBtn) -> {
			te.ySize -= KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			yLabel.setText(Integer.toString(te.ySize));
			return true;
		}, "yMinus");
		UIButtonText yPlus = new UIButtonText("+", (btn, mouseBtn) -> {
			te.ySize += KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			yLabel.setText(Integer.toString(te.ySize));
			return true;
		}, "yPlus");
		yStack.add(yMinus);
		yStack.add(yLabel);
		yStack.add(yPlus);
		horizontalStack.add(yStack);

		horizontalStack.add(new UILabel("x", "label2"));

//		Z
		UIStackView zStack = new UIStackView("zStack");
		UILabel zLabel = new UILabel(Integer.toString(te.zSize), "zLabel");
		UIButtonText zMinus = new UIButtonText("-", (btn, mouseBtn) -> {
			te.zSize -= KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			zLabel.setText(Integer.toString(te.zSize));
			return true;
		}, "zMinus");
		UIButtonText zPlus = new UIButtonText("+", (btn, mouseBtn) -> {
			te.zSize += KeyboardHelper.isShiftPressed() ? 5 : 1;
			te.sync();
			zLabel.setText(Integer.toString(te.zSize));
			return true;
		}, "zPlus");
		zStack.add(zMinus);
		zStack.add(zLabel);
		zStack.add(zPlus);
		horizontalStack.add(zStack);

		top.add(horizontalStack);

		UIButtonText copy = new UIButtonText("Copy JSON", (btn, mouseBtn) -> {
			DesktopUtils.copyToClipboard(StructureManager.INSTANCE.toJson(new Structure(te.getWorld(), te.getBox())));
			return true;
		}, "copy");

		top.add(copy);


		root.add(top);

		return new UIBuilder().add(root).style(ShadowMC.modId + ":structurecreator").createContainer(new ContainerPlayerInv(pos, inventory));
	}

}
