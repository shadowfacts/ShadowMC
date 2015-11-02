package net.shadowfacts.shadowmc.anvil;

import lombok.Getter;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shadowfacts
 */
public class AnvilManager {

	@Getter
	private static AnvilManager instance = new AnvilManager();

	@Getter
	private List<AnvilRecipe> recipes = new ArrayList<>();

	private AnvilManager() {}

	public AnvilRecipe addRecipe(ItemStack left, ItemStack right, ItemStack result, int xp) {
		AnvilRecipe recipe = new BasicAnvilRecipe(left, right, result, xp);
		recipes.add(recipe);
		return recipe;
	}

	public Optional<AnvilRecipe> getRecipe(ItemStack left, ItemStack right) {
		return recipes.stream().filter(recipe -> recipe.matches(left, right)).findFirst();
	}

	public Optional<ItemStack> getOutput(ItemStack left, ItemStack right) {
		Optional<AnvilRecipe> recipe = getRecipe(left, right);
		if (recipe.isPresent()) {
			return Optional.of(recipe.get().getResult());
		}

		return Optional.empty();
	}

	public int getCost(ItemStack left, ItemStack right) {
		Optional<AnvilRecipe> recipe = getRecipe(left, right);
		if (recipe.isPresent()) {
			return recipe.get().getXPCost();
		}
		return 0;
	}

}
