package dev.prefex.reforested.machines.carpenter;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.helpers.ExtendedRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterRecipe extends ExtendedRecipe {
	private final int GRID_WIDTH = 3;
	private final int GRID_HEIGHT = 3;

	//final FluidStack fluid;
	final RawShapedRecipe raw;
	final Ingredient frame;
	final ItemStack result;

	public CarpenterRecipe(/*FluidStack fluid, */RawShapedRecipe raw, Ingredient frame, ItemStack result) {
		/*this.fluid = fluid;*/
		this.raw = raw;
		this.frame = frame;
		this.result = result;
	}

	public FluidStack getFluid() {
		throw new UnsupportedOperationException("Not implemented");
		//return fluid;
	}

	public DefaultedList<Ingredient> getInput() {
		return raw.ingredients();
	}

	public Ingredient getFrame() {
		return frame;
	}

	@Override
	public ItemStack[] getOutput(DynamicRegistryManager registryManager) {
		return new ItemStack[] { result };
	}

	@Override
	public ItemStack[] produce(Inventory inventory, DynamicRegistryManager registryManager) {
		return new ItemStack[] { result };
	}

	@Override
	public boolean matches(Inventory recipeInputInventory, World world) {
		for(int i = 0; i <= GRID_WIDTH - this.raw.width(); ++i) {
			for(int j = 0; j <= GRID_HEIGHT - this.raw.height(); ++j) {
				if (this.matchesPattern(recipeInputInventory, i, j, true)) {
					return true;
				}

				if (this.matchesPattern(recipeInputInventory, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean matchesPattern(Inventory inv, int offsetX, int offsetY, boolean flipped) {
		DefaultedList<Ingredient> ingredients = this.raw.ingredients();
		for(int i = 0; i < GRID_WIDTH; ++i) {
			for(int j = 0; j < GRID_HEIGHT; ++j) {
				int k = i - offsetX;
				int l = j - offsetY;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.raw.width() && l < this.raw.height()) {
					if (flipped) {
						ingredient = ingredients.get(this.raw.width() - k - 1 + l * this.raw.width());
					} else {
						ingredient = ingredients.get(k + l * this.raw.width());
					}
				}

				if (!ingredient.test(inv.getStack(i + j * GRID_WIDTH))) {
					return false;
				}
			}
		}

		return true;
	}

	// This is only used for crafting table like containers
	public boolean fits(int width, int height) {
		return width >= this.raw.width() && height >= this.raw.height();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return new Serializer();
	}

	public static class Serializer implements RecipeSerializer<CarpenterRecipe> {
		public static final Codec<CarpenterRecipe> CODEC = RecordCodecBuilder.create((instance) ->
			instance.group(
				//FluidStack.CODEC.fieldOf("fluid").forGetter(CarpenterRecipe::getFluid), // TODO: implement fluid stack
				RawShapedRecipe.CODEC.forGetter((recipe) -> recipe.raw),
				Ingredient.ALLOW_EMPTY_CODEC.fieldOf("frame").forGetter((recipe) -> recipe.frame),
				ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter((recipe) -> recipe.result)
		).apply(instance, CarpenterRecipe::new));

		@Override
		public Codec<CarpenterRecipe> codec() {
			return CODEC;
		}

		@Override
		public CarpenterRecipe read(PacketByteBuf packetByteBuf) {
			//FluidStack fluidStack = new FluidStack(); // TODO: implement fluid stack

			RawShapedRecipe raw = RawShapedRecipe.readFromBuf(packetByteBuf);
			Ingredient frame = Ingredient.fromPacket(packetByteBuf);

			ItemStack result = packetByteBuf.readItemStack();

			return new CarpenterRecipe(/*fluidStack, */raw, frame, result);
		}

		public void write(PacketByteBuf packetByteBuf, CarpenterRecipe shapedRecipe) {
			// TODO: implement fluid stack

			shapedRecipe.raw.writeToBuf(packetByteBuf);
			shapedRecipe.frame.write(packetByteBuf);

			packetByteBuf.writeItemStack(shapedRecipe.result);
		}

		public static final CarpenterRecipe.Serializer INSTANCE = new CarpenterRecipe.Serializer();
	}

	// Registry boilerplate
	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
	public static class Type implements RecipeType<CarpenterRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final Identifier ID = id("carpenter");
	}
}
