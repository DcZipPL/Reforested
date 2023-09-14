package dev.prefex.reforested.machines.carpenter;

import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.helpers.ExtendedRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterRecipe extends ExtendedRecipe {
	private final FluidStack fluid;
	private final DefaultedList<Ingredient> input;
	private final Ingredient frame;
	private final ItemStack output;

	public CarpenterRecipe(FluidStack fluid, DefaultedList<Ingredient> input, Ingredient frame, ItemStack output) {
		this.fluid = fluid;
		this.input = input;
		this.frame = frame;
		this.output = output;
	}

	public FluidStack getFluid() {
		return fluid;
	}

	public DefaultedList<Ingredient> getInput() {
		return input;
	}

	public Ingredient getFrame() {
		return frame;
	}

	@Override
	public ItemStack[] getResult(DynamicRegistryManager registryManager) {
		return new ItemStack[] { output };
	}

	@Override
	public ItemStack[] produce(Inventory inventory, DynamicRegistryManager registryManager) {
		return new ItemStack[] { output };
	}

	@Override
	public boolean matches(Inventory inventory, World world) {
		return true; // TODO: Use input.test
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	// Registry boilerplate
	@Override
	public Identifier getId() {
		return Type.ID;
	}
	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
	public static class Type implements RecipeType<CarpenterRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final Identifier ID = id("carpenter_recipe");
	}
}
