package dev.prefex.yokai.helpers;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;

public abstract class ExtendedRecipe implements Recipe<Inventory> {
	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		return produce(inventory, registryManager)[0];
	}

	@Override
	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return getResult(registryManager)[0];
	}

	public abstract ItemStack[] getResult(DynamicRegistryManager registryManager);

	public abstract ItemStack[] produce(Inventory inventory, DynamicRegistryManager registryManager);
}
