package dev.prefex.reforested.machines.carpenter;

import com.google.gson.JsonObject;
import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.helpers.ExtendedRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Map;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterRecipe extends ExtendedRecipe {
	private final int GRID_WIDTH = 3;
	private final int GRID_HEIGHT = 3;

	final Identifier id;
	final int width;
	final int height;
	final FluidStack fluid;
	final DefaultedList<Ingredient> input;
	final Ingredient frame;
	final ItemStack output;

	public CarpenterRecipe(Identifier id, FluidStack fluid, DefaultedList<Ingredient> input, Ingredient frame, ItemStack output, int width, int height) {
		this.id = id;
		this.fluid = fluid;
		this.input = input;
		this.frame = frame;
		this.output = output;
		this.width = width;
		this.height = height;
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
	public boolean matches(Inventory recipeInputInventory, World world) {
		for(int i = 0; i <= GRID_WIDTH - this.width; ++i) {
			for(int j = 0; j <= GRID_HEIGHT - this.height; ++j) {
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
		for(int i = 0; i < GRID_WIDTH; ++i) {
			for(int j = 0; j < GRID_HEIGHT; ++j) {
				int k = i - offsetX;
				int l = j - offsetY;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
					if (flipped) {
						ingredient = (Ingredient)this.input.get(this.width - k - 1 + l * this.width);
					} else {
						ingredient = (Ingredient)this.input.get(k + l * this.width);
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
		return width >= this.width && height >= this.height;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return new Serializer();
	}

	public static class Serializer implements RecipeSerializer<CarpenterRecipe> {
		public CarpenterRecipe read(Identifier identifier, JsonObject jsonObject) {
			FluidStack fluidStack = new FluidStack(); // TODO: implement fluid stack

			Map<String, Ingredient> map = ShapedRecipe.readSymbols(JsonHelper.getObject(jsonObject, "key"));
			String[] strings = ShapedRecipe.removePadding(ShapedRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));
			int i = strings[0].length();
			int j = strings.length;
			DefaultedList<Ingredient> defaultedList = ShapedRecipe.createPatternMatrix(strings, map, i, j);

			Ingredient frame = Ingredient.fromJson(jsonObject.get("frame"), true);
			ItemStack result = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
			return new CarpenterRecipe(identifier, fluidStack, defaultedList, frame, result, i, j);
		}

		public CarpenterRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
			FluidStack fluidStack = new FluidStack(); // TODO: implement fluid stack

			int i = packetByteBuf.readVarInt();
			int j = packetByteBuf.readVarInt();
			DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);

			defaultedList.replaceAll(ignored -> Ingredient.fromPacket(packetByteBuf));

			Ingredient frame = Ingredient.fromPacket(packetByteBuf);
			ItemStack result = packetByteBuf.readItemStack();
			return new CarpenterRecipe(identifier, fluidStack, defaultedList, frame, result, i, j);
		}

		public void write(PacketByteBuf packetByteBuf, CarpenterRecipe shapedRecipe) {

			for (Ingredient ingredient : shapedRecipe.input) {
				ingredient.write(packetByteBuf);
			}

			packetByteBuf.writeItemStack(shapedRecipe.output);
		}

		public static final CarpenterRecipe.Serializer INSTANCE = new CarpenterRecipe.Serializer();
	}

	// Registry boilerplate
	@Override
	public Identifier getId() {
		return id;
	}
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
