package dev.prefex.reforested.machines.carpenter;

import dev.prefex.yokai.fluid.FluidStack;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public record CarpenterRecipe(Identifier id, FluidStack fluid, DefaultedList<Ingredient> input, Ingredient frame, ItemStack output) {

}
