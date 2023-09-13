package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.fabricmc.fabric.api.resource.conditions.v1.*;

import java.util.function.Consumer;

public class ReforestedRecipeProvider extends FabricRecipeProvider {

	public ReforestedRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	private ConditionJsonProvider[] conditions = null;

	private void startCompat(String... modid) {
		conditions = new ConditionJsonProvider[] { DefaultResourceConditions.allModsLoaded(modid) };
	}

	private void bowlAndStickCompat(String with, String... without) {
		conditions = new ConditionJsonProvider[] {
				DefaultResourceConditions.allModsLoaded(with),
				DefaultResourceConditions.not(DefaultResourceConditions.anyModLoaded(without))
		};
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {
		generateCraftingRecipes(exporter);

		bowlAndStickCompat("lightestlamp", "techreborn", "modern_industrialization", "indrev");
		addShapelessCriteria(
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCOOP)
						.input(dev.prefex.lightestlamp.init.ModItems.STICKANDBOWL)
						.input(Items.COPPER_INGOT)
						.input(Items.COPPER_INGOT)
						.input(Items.COPPER_INGOT)
						.input(ModItems.TIN_INGOT),
				ModItems.STURDY_CASING
		).offerTo(withConditions(exporter, conditions), Registries.ITEM.getId(ModItems.SCOOP));
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
						.input('#', ModItems.BRONZE_INGOT),
				ModItems.BRONZE_INGOT
		).offerTo(exporter, Registries.ITEM.getId(ModItems.STURDY_CASING));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.THERMIONIC_FABRICATOR)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("ICI")
						.input('I', Items.GOLD_INGOT)
						.input('G', Blocks.GLASS)
						.input('C', Blocks.CHEST)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.THERMIONIC_FABRICATOR));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CARPENTER)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', ModItems.BRONZE_INGOT)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.CARPENTER));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CENTRIFUGE)
						.pattern("IGI")
						.pattern("I#I")
						.pattern("IGI")
						.input('I', Items.COPPER_INGOT)
						.input('G', Blocks.GLASS)
						.input('#', ModItems.STURDY_CASING),
				ModItems.STURDY_CASING
		).offerTo(exporter, Registries.BLOCK.getId(ModBlocks.CENTRIFUGE));

		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOISTENER)
						.pattern("IGI")
						.pattern("G#G")
						.pattern("IGI")
						.input('I', ModItems.COPPER_GEAR)
						.input('G', Blocks.GLASS)
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
						.input('I', ModItems.TIN_INGOT)
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
						.input('I', ModItems.BRONZE_INGOT)
						.input('G', Blocks.GLASS)
						.input('P', Blocks.PISTON)
						.input('#', ModItems.BRONZE_GEAR),
				ModItems.BRONZE_INGOT
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
