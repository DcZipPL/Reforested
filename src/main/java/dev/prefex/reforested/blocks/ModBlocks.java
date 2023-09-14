package dev.prefex.reforested.blocks;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.machines.carpenter.CarpenterBlock;
import dev.prefex.reforested.util.WoodSet;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

@SuppressWarnings("unused")
public class ModBlocks {

	public static final Block MEADOW_BEE_NEST = registerBeeNest("meadow");
	public static final Block DESERT_BEE_NEST = registerBeeNest("desert");
	public static final Block JUNGLE_BEE_NEST = registerBeeNest("jungle");
	public static final Block SWAMP_BEE_NEST = registerBeeNest("swamp");
	public static final Block MOUNTAIN_BEE_NEST = registerBeeNest("mountain");
	public static final Block SNOW_BEE_NEST = registerBeeNest("snow");

	// TODO: Add rest of Forestry blocks but with red tooltip "Not implemented yet!"

	public static final Block CARPENTER = register("carpenter", new CarpenterBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.COPPER)));
	public static final WipBlock SQUEEZER = (WipBlock) register("squeezer", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock CENTRIFUGE = (WipBlock) register("centrifuge", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock STILL = (WipBlock) register("still", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock FERMENTER = (WipBlock) register("fermenter", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock MOISTENER = (WipBlock) register("moistener", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock BOTTLER = (WipBlock) register("bottler", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final WipBlock APIARIST_CHEST = (WipBlock) register("apiarist_chest", new WipBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));
	public static final WipBlock WORKTABLE = (WipBlock) register("worktable", new WipBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
	public static final WipBlock THERMIONIC_FABRICATOR = (WipBlock) register("thermionic_fabricator", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock RAINTANK = (WipBlock) register("raintank", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final WipBlock REDSTONE_ENGINE = (WipBlock) register("redstone_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
	public static final WipBlock STIRLING_ENGINE = (WipBlock) register("stirling_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));
	public static final WipBlock COMBUSTION_ENGINE = (WipBlock) register("combustion_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock CREATIVE_ENGINE = (WipBlock) register("creative_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

	public static final WipBlock CLOCKWORK_ENGINE = (WipBlock) register("clockwork_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock ELECTRICAL_ENGINE = (WipBlock) register("electrical_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock BIOGAS_ENGINE = (WipBlock) register("biogas_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
	public static final WipBlock PEAT_ENGINE = (WipBlock) register("peat_engine", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final WipBlock BIO_GENERATOR = (WipBlock) register("bio_generator", new WipBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

	public static final WipBlock FARM_BLOCK = (WipBlock) register("farm_block", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final WipBlock FARM_VALVE = (WipBlock) register("farm_valve", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final WipBlock FARM_HATCH = (WipBlock) register("farm_hatch", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final WipBlock FARM_CONTROL = (WipBlock) register("farm_control", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final WipBlock FARM_GEARBOX = (WipBlock) register("farm_gearbox", new WipBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final WipBlock HUMUS = (WipBlock) register("humus", new WipBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block BOG_EARTH = register("bog_earth", new BogEarthBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final WipBlock APATITE_ORE = (WipBlock) register("apatite_ore", new WipBlock(AbstractBlock.Settings.copy(Blocks.COAL_ORE)));
	public static final WipBlock BEE_HOUSE = (WipBlock) register("bee_house", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEEHIVE)));
	public static final WipBlock APIARY = (WipBlock) register("apiary", new WipBlock(AbstractBlock.Settings.copy(Blocks.BEEHIVE)));

	public static final WoodSet HAWTHORN = new WoodSet("hawthorn", MapColor.BROWN, MapColor.BROWN);
	public static final WoodSet DAIR = new WoodSet("dair", MapColor.BROWN, MapColor.BROWN);
	public static final WoodSet WILLOW = new WoodSet("willow", MapColor.BROWN, MapColor.BROWN);
	public static final WoodSet NUM_NUM = new WoodSet("num_num", MapColor.BLUE, MapColor.LAPIS_BLUE);
	public static final WoodSet EUCALYPTUS = new WoodSet("eucalyptus", MapColor.RAW_IRON_PINK, MapColor.TEAL);

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
