package dev.prefex.reforested.datagen;

import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class ReforestedItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ReforestedItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		addTag(ModTags.TIN_INGOTS, ModItems.TIN_INGOT);
		addTag(ModTags.BRONZE_INGOTS, ModItems.BRONZE_INGOT);
		addTag(ModTags.COPPER_GEARS, ModItems.COPPER_GEAR);
		addTag(ModTags.TIN_GEARS, ModItems.TIN_GEAR);
		addTag(ModTags.BRONZE_GEARS, ModItems.BRONZE_GEAR);
		addTag(ModTags.STONE_GEARS, ModItems.STONE_GEAR);
		addTag(ModTags.IRON_GEARS, ModItems.IRON_GEAR);
		addTag(ModTags.WOODEN_GEARS, ModItems.WOODEN_GEAR);

		addTag(ModTags.GEARS, ModItems.COPPER_GEAR, ModItems.TIN_GEAR, ModItems.BRONZE_GEAR, ModItems.STONE_GEAR, ModItems.IRON_GEAR, ModItems.WOODEN_GEAR);
		addTag(ModTags.INGOTS, ModItems.TIN_INGOT, ModItems.BRONZE_INGOT);

		addTag(ModTags.GLASS, Items.GLASS, Items.TINTED_GLASS, // TODO: Move ModBlocks.GLOWING_GLASS_BLOCK.asItem() to Lightest lamps
				Items.GRAY_STAINED_GLASS, Items.BLACK_STAINED_GLASS, Items.BLUE_STAINED_GLASS, Items.GREEN_STAINED_GLASS, Items.BROWN_STAINED_GLASS, Items.CYAN_STAINED_GLASS,
				Items.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_GRAY_STAINED_GLASS, Items.LIME_STAINED_GLASS, Items.MAGENTA_STAINED_GLASS, Items.ORANGE_STAINED_GLASS,
				Items.PINK_STAINED_GLASS, Items.PURPLE_STAINED_GLASS, Items.RED_STAINED_GLASS, Items.WHITE_STAINED_GLASS, Items.YELLOW_STAINED_GLASS);

		addTag(ModTags.CHEST, Items.CHEST);
	}

	private void addTag(TagKey<Item> tag, Item... items) {
		FabricTagBuilder tagBuilder = getOrCreateTagBuilder(tag);
		for (Item item : items) {
			tagBuilder.add(item);
		}
	}
}