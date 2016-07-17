package test;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.shadowmc.inventory.ContainerPlayerInv;
import net.shadowfacts.shadowmc.ui.element.UILabel;
import net.shadowfacts.shadowmc.ui.element.UIItemStack;
import net.shadowfacts.shadowmc.ui.element.button.UIImage;
import net.shadowfacts.shadowmc.ui.element.view.UIFixedView;
import net.shadowfacts.shadowmc.ui.element.view.UIListView;
import net.shadowfacts.shadowmc.ui.element.view.UIStackView;
import net.shadowfacts.shadowmc.ui.util.UIBuilder;

import java.awt.Color;

import static net.shadowfacts.shadowmc.ui.style.UIAttribute.*;
import static net.shadowfacts.shadowmc.ui.style.UIHorizontalLayoutMode.*;
import static net.shadowfacts.shadowmc.ui.style.UIVerticalLayoutMode.*;

/**
 * @author shadowfacts
 */
public class UIContainerTest {

	public static GuiScreen create(InventoryPlayer playerInv) {
		UIFixedView view = new UIFixedView(176, 166, "main");

		UIImage texture = new UIImage(new ResourceLocation("modtest:textures/gui/test.png"), 176, 166, "background");
		view.add(texture);

		UIFixedView topPanel = new UIFixedView(176, 166 / 2, "top-panel");
		topPanel.setStyle(VERTICAL_LAYOUT, TOP);

		UIListView list = new UIListView(topPanel.width - 10, topPanel.height - 10, "list");
		list.setStyle(VERTICAL_LAYOUT, TOP);
		list.setStyle(HORIZONTAL_LAYOUT, LEFT);
		list.setStyle(MARGIN_TOP, 5);
		list.setStyle(MARGIN_LEFT, 5);

		ItemStack[] stacks = {new ItemStack(Items.IRON_INGOT), new ItemStack(Items.REDSTONE), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.PLANKS), new ItemStack(Blocks.BEACON), new ItemStack(Blocks.DRAGON_EGG)};
//		ItemStack[] stacks = {new ItemStack(Items.IRON_INGOT), new ItemStack(Items.REDSTONE), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.PLANKS)};

		String[] subtext = {"Testerino!", "Test 2!", "Oooh, shiny!", "Precioussss!", "Woody", "Oooh, shinier!", "Draconic"};

		for (int i = 0; i < stacks.length; i++) {

			UIFixedView fixed = new UIFixedView(topPanel.width, 32, "item" + i);

			UIItemStack stack = new UIItemStack(stacks[i], 32, 32, "stack" + i);
			stack.setTooltip(true);
			stack.setStyle(HORIZONTAL_LAYOUT, LEFT);
			stack.setStyle(VERTICAL_LAYOUT, TOP);
			stack.setStyle(MARGIN_LEFT, 24);
			fixed.add(stack);

			UIStackView stackView = new UIStackView("stack-view" + i);
			stackView.setStyle(HORIZONTAL_LAYOUT, RIGHT);
			stackView.setStyle(STACK_SPACING, 7);

			UILabel title = new UILabel(stacks[i].getDisplayName(), "title" + i);
			title.setStyle(TEXT_UNDERLINE, true);
			title.setStyle(TEXT_COLOR, new Color(0x373737));
			title.setStyle(HORIZONTAL_LAYOUT, RIGHT);

			stackView.add(title);

			UILabel subtextLabel = new UILabel(subtext[i], "subtext" + i);
			subtextLabel.setStyle(HORIZONTAL_LAYOUT, RIGHT);
			stackView.add(subtextLabel);

			fixed.add(stackView);

			list.add(fixed);

		}

		topPanel.add(list);

		view.add(topPanel);

		return new UIBuilder().add(view).style("modtest:uitest").createContainer(new ContainerPlayerInv(playerInv.player.getPosition(), playerInv));
	}

}
