package dev.prefex.reforested.datagen;

import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
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
		addTag(ModTags.GEARS, ModItems.COPPER_GEAR, ModItems.TIN_GEAR, ModItems.BRONZE_GEAR);
	}

	private void addTag(TagKey<Item> tag, Item... items) {
		FabricTagBuilder tagBuilder = getOrCreateTagBuilder(tag);
		for (Item item : items) {
			tagBuilder.add(item);
		}
	}
}