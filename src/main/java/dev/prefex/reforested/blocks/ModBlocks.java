package dev.prefex.reforested.blocks;

import dev.prefex.reforested.Reforested;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

@SuppressWarnings("unused")
public class ModBlocks {

	public static final Block MEADOW_BEE_NEST = registerBeeNest("meadow");
	public static final Block FOREST_BEE_NEST = registerBeeNest("forest");
	public static final Block DESERT_BEE_NEST = registerBeeNest("desert");
	public static final Block JUNGLE_BEE_NEST = registerBeeNest("jungle");
	public static final Block SWAMP_BEE_NEST = registerBeeNest("swamp");
	public static final Block MOUNTAIN_BEE_NEST = registerBeeNest("mountain");
	public static final Block SNOW_BEE_NEST = registerBeeNest("snow");

	// TODO: Add rest of Forestry blocks but with red tooltip "Not implemented yet!"

	public static final Block CARPENTER = register("carpenter", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block SQUEEZER = register("squeezer", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block CENTRIFUGE = register("centrifuge", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block STILL = register("still", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block FERMENTER = register("fermenter", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block MOISTENER = register("moistener", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block BOTTLER = register("bottler", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final Block APIARIST_CHEST = register("apiarist_chest", new WipBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));
	public static final Block WORKTABLE = register("worktable", new WipBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
	public static final Block THERMIONIC_FABRICATOR = register("thermionic_fabricator", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block RAINTANK = register("raintank", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final Block REDSTONE_ENGINE = register("redstone_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
	public static final Block STIRLING_ENGINE = register("stirling_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));
	public static final Block COMBUSTION_ENGINE = register("combustion_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block CREATIVE_ENGINE = register("creative_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

	public static final Block CLOCKWORK_ENGINE = register("clockwork_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block ELECTRICAL_ENGINE = register("electrical_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block BIOGAS_ENGINE = register("biogas_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final Block PEAT_ENGINE = register("peat_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final Block BIO_GENERATOR = register("bio_generator", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final Block FARM_BLOCK = register("farm_block", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block FARM_VALVE = register("farm_valve", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block FARM_HATCH = register("farm_hatch", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block FARM_CONTROL = register("farm_control", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block FARM_GEARBOX = register("farm_gearbox", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block HUMUS = register("humus", new WipBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block BOG_EARTH = register("bog_earth", new WipBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block APATITE_ORE = register("apatite_ore", new WipBlock(AbstractBlock.Settings.copy(Blocks.COAL_ORE)));
	public static final Block BEE_HOUSE = register("bee_house", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEEHIVE)));
	public static final Block APIARY = register("apiary", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEEHIVE)));

	public static Block registerBeeNest(String name) {
		return register(name + "_bee_nest", new BeehiveBlock(AbstractBlock.Settings.copy(Blocks.BEE_NEST)));
	}

	public static Block register(String name, Block block) {
		Block self = Registry.register(Registries.BLOCK, Reforested.id(name), block);
		Reforested.GROUP_ITEMS.add(new ItemStack(
				Registry.register(Registries.ITEM, Reforested.id(name), new BlockItem(self, new BlockItem.Settings()))
		));
		return self;
	}

	public static void init() {}
}
