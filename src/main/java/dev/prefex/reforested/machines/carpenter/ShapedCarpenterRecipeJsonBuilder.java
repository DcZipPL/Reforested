package dev.prefex.reforested.machines.carpenter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// TODO: Parsing error loading custom advancement <RECIPE>: Advancement criteria cannot be empty
public class ShapedCarpenterRecipeJsonBuilder extends RecipeJsonBuilder implements CraftingRecipeJsonBuilder {
	private String group = "";
	private final Ingredient frame;
	private final Item output;
	private final int count;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> inputs = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.createUntelemetered();

	public ShapedCarpenterRecipeJsonBuilder(Ingredient frame, ItemConvertible output, int count) {
		this.frame = frame;
		this.output = output.asItem();
		this.count = count;
	}

	public static ShapedCarpenterRecipeJsonBuilder create(Ingredient frame, ItemConvertible output) {
		return ShapedCarpenterRecipeJsonBuilder.create(frame, output, 1);
	}

	public static ShapedCarpenterRecipeJsonBuilder create(Ingredient frame, ItemConvertible output, int count) {
		return new ShapedCarpenterRecipeJsonBuilder(frame, output, count);
	}

	public ShapedCarpenterRecipeJsonBuilder input(Character c, TagKey<Item> tag) {
		return this.input(c, Ingredient.fromTag(tag));
	}

	public ShapedCarpenterRecipeJsonBuilder input(Character c, ItemConvertible itemProvider) {
		return this.input(c, Ingredient.ofItems(itemProvider));
	}

	public ShapedCarpenterRecipeJsonBuilder input(Character c, Ingredient ingredient) {
		if (this.inputs.containsKey(c)) {
			throw new IllegalArgumentException("Symbol '" + c + "' is already defined!");
		}
		if (c.charValue() == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		}
		this.inputs.put(c, ingredient);
		return this;
	}

	public ShapedCarpenterRecipeJsonBuilder pattern(String patternStr) {
		if (!this.pattern.isEmpty() && patternStr.length() != this.pattern.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		}
		this.pattern.add(patternStr);
		return this;
	}

	@Override
	public ShapedCarpenterRecipeJsonBuilder criterion(String string, CriterionConditions criterionConditions) {
		this.advancementBuilder.criterion(string, criterionConditions);
		return this;
	}

	@Override
	public CraftingRecipeJsonBuilder group(@Nullable String group) {
		this.group = group;
		return this;
	}

	@Override
	public Item getOutputItem() {
		return this.output;
	}

	@Override
	public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
		this.validate(recipeId);
		exporter.accept(new ShapedCarpenterRecipeJsonBuilder.ShapedCarpenterRecipeJsonProvider(
				recipeId,
				this.group,
				this.frame,
				this.output,
				this.count,
				this.pattern,
				this.inputs,
				this.advancementBuilder,
				recipeId.withPrefixedPath("recipes/carpenter/")));
	}

	private void validate(Identifier recipeId) {
		if (this.pattern.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + recipeId + "!");
		}
		HashSet<Character> set = Sets.newHashSet(this.inputs.keySet());
		set.remove(Character.valueOf(' '));
		for (String string : this.pattern) {
			for (int i = 0; i < string.length(); ++i) {
				char c = string.charAt(i);
				if (!this.inputs.containsKey(Character.valueOf(c)) && c != ' ') {
					throw new IllegalStateException("Pattern in recipe " + recipeId + " uses undefined symbol '" + c + "'");
				}
				set.remove(Character.valueOf(c));
			}
		}
		if (!set.isEmpty()) {
			throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + recipeId);
		}
		if (this.pattern.size() == 1 && this.pattern.get(0).length() == 1) {
			throw new IllegalStateException("Shaped recipe " + recipeId + " only takes in a single item - should it be a shapeless recipe instead?");
		}
	}

	static class ShapedCarpenterRecipeJsonProvider
			extends RecipeJsonBuilder.CraftingRecipeJsonProvider {
		private final Identifier recipeId;
		private final String group;
		private final Ingredient frame;
		private final Item output;
		private final int resultCount;
		private final List<String> pattern;
		private final Map<Character, Ingredient> inputs;
		private final Advancement.Builder advancementBuilder;
		private final Identifier advancementId;

		public ShapedCarpenterRecipeJsonProvider(Identifier recipeId, String group, Ingredient frame, Item output, int resultCount, List<String> pattern, Map<Character, Ingredient> inputs, Advancement.Builder advancementBuilder, Identifier advancementId) {
			super(CraftingRecipeCategory.MISC); // TODO: Remove this
			this.recipeId = recipeId;
			this.group = group;
			this.frame = frame;
			this.output = output;
			this.resultCount = resultCount;
			this.pattern = pattern;
			this.inputs = inputs;
			this.advancementBuilder = advancementBuilder;
			this.advancementId = advancementId;
		}

		@Override
		public void serialize(JsonObject json) {
			super.serialize(json);

			// Group
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			// Pattern
			JsonArray patternJson = new JsonArray();
			for (String string : this.pattern) {
				patternJson.add(string);
			}
			json.add("pattern", patternJson);

			// Inputs
			JsonObject inputsJson = new JsonObject();
			for (Map.Entry<Character, Ingredient> entry : this.inputs.entrySet()) {
				inputsJson.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}
			json.add("key", inputsJson);

			// Frame !new
			json.add("frame", this.frame.toJson());

			// Result
			JsonObject resultJson = new JsonObject();
			resultJson.addProperty("item", Registries.ITEM.getId(this.output).toString());
			if (this.resultCount > 1) {
				resultJson.addProperty("count", this.resultCount);
			}
			json.add("result", resultJson);
		}

		@Override
		public RecipeSerializer<?> getSerializer() {
			return CarpenterRecipe.Serializer.INSTANCE;
		}

		@Override
		public Identifier getRecipeId() {
			return this.recipeId;
		}

		@Override
		@Nullable
		public JsonObject toAdvancementJson() {
			return this.advancementBuilder.toJson();
		}

		@Override
		@Nullable
		public Identifier getAdvancementId() {
			return this.advancementId;
		}
	}
}
