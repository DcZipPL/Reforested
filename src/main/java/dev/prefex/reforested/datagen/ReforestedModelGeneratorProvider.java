package dev.prefex.reforested.datagen;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.items.HoneycombItem;
import dev.prefex.reforested.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ReforestedModelGeneratorProvider extends FabricModelProvider {

	ReforestedModelGeneratorProvider(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ReforestedDataGenerator.executeBeeNestFunction(block -> {
			blockStateModelGenerator.registerBeehive(block, this::beeNestMap);
		});
		ReforestedDataGenerator.executeWipBlockFunction(blockStateModelGenerator::registerSimpleCubeAll);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (var comb : ModItems.HONEYCOMBS) {
			itemModelGenerator.register(comb, new Model(Optional.of(new Identifier("reforested", "item/honeycomb")), Optional.empty()));
		}
		for (var item : Reforested.GROUP_ITEMS) {
			if (item.getItem() instanceof HoneycombItem) continue;
			if (item.getItem() instanceof BlockItem) continue;

			ReforestedDataGenerator.LOGGER.info("Registering item model for " + item.getItem().getTranslationKey());
			itemModelGenerator.register(item.getItem(), Models.GENERATED);
		}
	}

	private static Identifier getSubId(Block block, String suffix) {
		Identifier identifier = Registries.BLOCK.getId(block);
		return identifier.withPath((path) -> "block/bee_nest/" + path + suffix);
	}

	private TextureMap beeNestMap(Block block) {
		return (new TextureMap()).put(TextureKey.SIDE, getSubId(block, "_side")).put(TextureKey.FRONT, getSubId(block, "_front")).put(TextureKey.TOP, getSubId(block, "_top")).put(TextureKey.BOTTOM, getSubId(block, "_bottom"));
	}
}