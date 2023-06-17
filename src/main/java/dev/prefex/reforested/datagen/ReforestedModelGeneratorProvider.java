package dev.prefex.reforested.datagen;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.items.HoneycombItem;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.items.PropolisItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ReforestedModelGeneratorProvider extends FabricModelProvider {

	ReforestedModelGeneratorProvider(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		// ...
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
			itemModelGenerator.register(item.getItem(), Models.GENERATED);
		}
	}
}