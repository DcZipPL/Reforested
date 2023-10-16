package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.fabricmc.fabric.api.resource.conditions.v1.*;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Consumer;

public class ReforestedRecipeProvider extends FabricRecipeProvider {

	public ReforestedRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	private ConditionJsonProvider[] conditions = null;

	private void withCompat(String... modid) {
		conditions = new ConditionJsonProvider[] { DefaultResourceConditions.allModsLoaded(modid) };
	}

	private void withoutCompat(String... without) {
		conditions = new ConditionJsonProvider[] {
				DefaultResourceConditions.not(DefaultResourceConditions.anyModLoaded(without))
		};
	}

	private void oneModCompat(String with, String... without) {
		conditions = new ConditionJsonProvider[] {
				DefaultResourceConditions.allModsLoaded(with),
				DefaultResourceConditions.not(DefaultResourceConditions.anyModLoaded(without))
		};
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {
		generateCraftingRecipes(exporter);

		//ReforestedDataGenerator.executeWoodSet(this::generateWoodRecipes, __ -> {});

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_GEAR)
						.pattern(" # ")
						.pattern("# #")
						.pattern(" # ")
						.input('#', ModTags.COBBLED),
				Items.COBBLESTONE
		).offerTo(exporter, Registries.ITEM.getId(ModItems.STONE_GEAR));

		createGearRecipe(exporter, Items.COPPER_INGOT, Items.COPPER_INGOT, ModItems.COPPER_GEAR);
		createGearRecipe(exporter, ModTags.TIN_INGOTS, ModItems.TIN_INGOT, ModItems.TIN_GEAR);
		createGearRecipe(exporter, ModTags.BRONZE_INGOTS, ModItems.BRONZE_INGOT, ModItems.BRONZE_GEAR);

		oneModCompat("lightestlamp", "techreborn", "modern_industrialization", "indrev");
		addShapelessCriteria(
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRONZE_INGOT, 4)
						.input(dev.prefex.lightestlamp.init.ModItems.STICKANDBOWL)
						.input(Items.COPPER_INGOT,3)
						.input(ModTags.TIN_INGOTS),
				ModItems.BRONZE_INGOT
		).offerTo(withConditions(exporter, conditions), Registries.ITEM.getId(ModItems.BRONZE_INGOT)+"_lightestlamps");

		withoutCompat("techreborn");
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CAN, 2)
						.pattern(" I ")
						.pattern("I I")
						.pattern(" I ")
						.input('I', ModTags.TIN_INGOTS),
				ModItems.BRONZE_INGOT
		).offerTo(withConditions(exporter, conditions), Registries.ITEM.getId(ModItems.CAN));

		withCompat("techreborn");
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CAN, 4)
						.pattern(" I ")
						.pattern("IGI")
						.pattern(" I ")
						.input('G', Items.GLASS)
						.input('I', ModTags.TIN_INGOTS),
				ModItems.BRONZE_INGOT
		).offerTo(withConditions(exporter, conditions), Registries.ITEM.getId(ModItems.CAN)+"_compat");
	}

	private void generateCraftingRecipes(Consumer<RecipeJsonProvider> exporter) {
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCOOP)
						.pattern("SWS")
						.pattern(" S ")
						.pattern(" S ")
						.input('S', Items.STICK)
						.input('W', ItemTags.WOOL),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.ITEM.getId(ModItems.SCOOP));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STURDY_CASING)
						.pattern("###")
						.pattern("# #")
						.pattern("###")
						.input('#', ModTags.BRONZE_INGOTS),
				ModItems.BRONZE_INGOT
		).offerTo(exporter, Registries.ITEM.getId(ModItems.STURDY_CASING));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.THERMIONIC_FABRICATOR)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("ICI")
						.input('I', Items.GOLD_INGOT)
						.input('G', Items.GLASS)
						.input('C', ModTags.CHEST)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.THERMIONIC_FABRICATOR));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CARPENTER)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', ModTags.BRONZE_INGOTS)
						.input('G', Items.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.CARPENTER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CENTRIFUGE)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', Items.COPPER_INGOT)
						.input('G', Items.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.CENTRIFUGE));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOISTENER)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("IGI")
						.input('I', ModItems.COPPER_GEAR)
						.input('G', Items.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.MOISTENER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FERMENTER)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("IGI")
						.input('I', ModItems.BRONZE_GEAR)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.FERMENTER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SQUEEZER)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', ModTags.TIN_INGOTS)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.SQUEEZER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STILL)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("IGI")
						.input('I', Items.REDSTONE)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.STILL));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BOTTLER)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("IGI")
						.input('I', ModItems.CAN)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.BOTTLER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BIO_GENERATOR)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', Items.GOLD_INGOT)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.BIO_GENERATOR));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BIOGAS_ENGINE)
						.pattern("III")
						.pattern(" G ")
						.pattern("#P#")
						.input('I', ModTags.BRONZE_INGOTS)
						.input('G', Blocks.GLASS)
						.input('P', Blocks.PISTON)
						.input('#', ModItems.BRONZE_GEAR),
				Items.PISTON
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.BIOGAS_ENGINE));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PEAT_ENGINE)
						.pattern("III")
						.pattern(" G ")
						.pattern("#P#")
						.input('I', Items.COPPER_INGOT)
						.input('G', Blocks.GLASS)
						.input('P', Blocks.PISTON)
						.input('#', ModItems.COPPER_GEAR),
				Items.COPPER_INGOT
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.PEAT_ENGINE));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLOCKWORK_ENGINE)
						.pattern("III")
						.pattern(" G ")
						.pattern("#PC")
						.input('I', Items.GOLD_INGOT)
						.input('G', Blocks.GLASS)
						.input('P', Blocks.PISTON)
						.input('C', Items.CLOCK)
						.input('#', ModItems.COPPER_GEAR),
				ModItems.COPPER_GEAR
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.CLOCKWORK_ENGINE));
	}

	private void createGearRecipe(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible unlock, Item output) {
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
						.pattern(" # ")
						.pattern("#G#")
						.pattern(" # ")
						.input('G', ModItems.STONE_GEAR)
						.input('#', input),
				unlock
		).offerTo(exporter, Registries.ITEM.getId(output));
	}

	private void createGearRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible unlock, Item output) {
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
						.pattern(" # ")
						.pattern("#G#")
						.pattern(" # ")
						.input('G', ModItems.STONE_GEAR)
						.input('#', input),
				unlock
		).offerTo(exporter, Registries.ITEM.getId(output));
	}

	private ShapedRecipeJsonBuilder addShapedCriteria(ShapedRecipeJsonBuilder builder, ItemConvertible... itemConvertible){
		for (ItemConvertible item : itemConvertible) {
			builder = builder.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item));
		}
		return builder;
	}

	private ShapelessRecipeJsonBuilder addShapelessCriteria(ShapelessRecipeJsonBuilder builder, ItemConvertible... itemConvertible){
		for (ItemConvertible item : itemConvertible) {
			builder = builder.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item));
		}
		return builder;
	}
}
