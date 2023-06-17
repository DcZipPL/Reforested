package dev.prefex.reforested.blocks;

import dev.prefex.reforested.Reforested;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {

	public static final Block MEADOW_BEE_NEST = registerBeeNest("meadow");
	public static final Block FOREST_BEE_NEST = registerBeeNest("forest");
	public static final Block DESERT_BEE_NEST = registerBeeNest("desert");
	public static final Block JUNGLE_BEE_NEST = registerBeeNest("jungle");
	public static final Block SWAMP_BEE_NEST = registerBeeNest("swamp");
	public static final Block MOUNTAIN_BEE_NEST = registerBeeNest("mountain");
	public static final Block SNOW_BEE_NEST = registerBeeNest("snow");

	public static Block registerBeeNest(String name) {
		return register(name + "_bee_nest", new BeehiveBlock(AbstractBlock.Settings.copy(net.minecraft.block.Blocks.BEEHIVE)));
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
