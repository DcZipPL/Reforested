package dev.prefex.reforested.datagen;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.items.HoneycombItem;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.items.PropolisItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReforestedModelGeneratorProvider extends FabricModelProvider {

	ReforestedModelGeneratorProvider(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();

		for (Field field : fields) {
			if (field.getName().contains("BEE_NEST")) {
				try {
					blockStateModelGenerator.registerBeehive((Block) field.get(null), TextureMap::sideFrontTopBottom);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (var comb : ModItems.HONEYCOMBS) {
			itemModelGenerator.register(comb, new Model(Optional.of(new Identifier("reforested", "item/honeycomb")), Optional.empty()));
		}
		for (var prop : ModItems.PROPOLISES) {
			itemModelGenerator.register(prop, new Model(Optional.of(new Identifier("reforested", "item/propolis_base")), Optional.empty()));
		}
		for (var item : Reforested.GROUP_ITEMS) {
			if (item.getItem() instanceof HoneycombItem) continue;
			if (item.getItem() instanceof PropolisItem) continue;
			if (item.getItem() instanceof BlockItem) continue;

			ReforestedDataGenerator.LOGGER.info("Registering item model for " + item.getItem().getTranslationKey());
			itemModelGenerator.register(item.getItem(), Models.GENERATED);
		}
	}
}