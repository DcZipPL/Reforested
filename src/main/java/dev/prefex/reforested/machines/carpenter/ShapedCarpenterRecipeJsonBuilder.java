package dev.prefex.reforested.machines.carpenter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ShapedCarpenterRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
	private String group = "";
	private final Ingredient frame;
	private final Item result;
	private final int count;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> inputs = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.createUntelemetered();

	public ShapedCarpenterRecipeJsonBuilder(Ingredient frame, ItemConvertible result, int count) {
		this.frame = frame;
		this.result = result.asItem();
		this.count = count;
	}

	public static ShapedCarpenterRecipeJsonBuilder create(Ingredient frame, ItemConvertible result) {
		return ShapedCarpenterRecipeJsonBuilder.create(frame, result, 1);
	}

	public static ShapedCarpenterRecipeJsonBuilder create(Ingredient frame, ItemConvertible result, int count) {
		return new ShapedCarpenterRecipeJsonBuilder(frame, result, count);
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
	public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
		this.advancementBuilder.criterion(name, criterion);
		return this;
	}

	@Override
	public CraftingRecipeJsonBuilder group(@Nullable String group) {
		this.group = group;
		return this;
	}

	@Override
	public Item getOutputItem() {
		return this.result;
	}

	@Override
	public void offerTo(RecipeExporter exporter, Identifier recipeId) {
		this.validate(recipeId);
		RawShapedRecipe rawShapedRecipe = RawShapedRecipe.create(inputs, pattern);
		CarpenterRecipe shapedRecipe = new CarpenterRecipe(
				rawShapedRecipe,
				frame,
				new ItemStack(this.result, this.count)
		);
		exporter.accept(recipeId, shapedRecipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/carpenter/")));
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
}
