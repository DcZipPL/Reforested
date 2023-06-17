package dev.prefex.reforested.datagen;

import dev.prefex.reforested.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.CopyStateFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.nbt.LootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ReforestedBlockLootTables extends FabricBlockLootTableProvider {
	protected ReforestedBlockLootTables(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(Blocks.BEE_NEST, scoopBeeNestDrops(Blocks.BEE_NEST));
	}

	public static LootTable.Builder scoopBeeNestDrops(Block drop) {
		return LootTable.builder().pool(
				LootPool.builder()
						.conditionally(WITH_SILK_TOUCH)
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(ItemEntry.builder(drop)
								.apply(CopyNbtLootFunction.builder((LootNbtProvider) ContextLootNbtProvider.BLOCK_ENTITY).withOperation("Bees", "BlockEntityTag.Bees"))
								.apply(CopyStateFunction.builder(drop).addProperty(BeehiveBlock.HONEY_LEVEL))
						)
		).pool(
				LootPool.builder()
						.conditionally(WITHOUT_SILK_TOUCH)
						.rolls(UniformLootNumberProvider.create(0.0F, 3.0F))
						.with(ItemEntry.builder(Items.HONEYCOMB))
		).pool(
				LootPool.builder()
						.conditionally(WITHOUT_SILK_TOUCH)
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(ItemEntry.builder(ModItems.BEE))
		);
	}
}
