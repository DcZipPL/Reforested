package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.BogEarthBlock;
import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.CopyStateFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.nbt.LootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.StringIdentifiable;

import java.util.ArrayList;

public class ReforestedBlockLootTables extends FabricBlockLootTableProvider {

	private ArrayList<Block> skipped = new ArrayList<>();

	protected ReforestedBlockLootTables(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(Blocks.BEE_NEST, scoopBeeNestDrops(Blocks.BEE_NEST));
		ReforestedDataGenerator.executeBeeNestFunction(block -> addDrop(skip(block), scoopBeeNestDrops(block)));

		addDrop(skip(ModBlocks.BOG_EARTH), dropsPeat(ModBlocks.BOG_EARTH, BogEarthBlock.MATURE));

		ReforestedDataGenerator.executeRegisterNotSkipped(this::addDrop, skipped);
		addPottedPlantDrops(ModBlocks.HAWTHORN.potted_sapling);
		addPottedPlantDrops(ModBlocks.DAIR.potted_sapling);
		addPottedPlantDrops(ModBlocks.WILLOW.potted_sapling);
		addPottedPlantDrops(ModBlocks.NUM_NUM.potted_sapling);
		addPottedPlantDrops(ModBlocks.EUCALYPTUS.potted_sapling);
	}

	public LootTable.Builder dropsPeat(Block drop, BooleanProperty property) {
		return LootTable.builder().pool(
				this.addSurvivesExplosionCondition(drop,
						LootPool.builder().rolls(
								ConstantLootNumberProvider.create(1.0f)
						).with(
								ItemEntry.builder(drop).conditionally(
										BlockStatePropertyLootCondition.builder(drop)
												.properties(StatePredicate.Builder.create().exactMatch(property, false))
								)
						)
				)
		).pool(
				this.addSurvivesExplosionCondition(ModItems.PEAT,
						LootPool.builder().rolls(
								ConstantLootNumberProvider.create(1.0f)
						).with(
								ItemEntry.builder(ModItems.PEAT).conditionally(
										BlockStatePropertyLootCondition.builder(drop)
												.properties(StatePredicate.Builder.create().exactMatch(property, true))
								)
						)
				)
		);
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
						.with(ItemEntry.builder(ModItems.BEE_PRINCESS))
		);
	}

	public Block skip(Block block) {
		skipped.add(block);
		return block;
	}
}
