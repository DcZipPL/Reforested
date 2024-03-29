package dev.prefex.reforested.util.registry;

import dev.prefex.reforested.Reforested;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RegisteredMetalOre {
	public Item dust;
	public Item nugget;
	public Item rawOre;
	public Block ore;
	public Block deepslateOre;
	public Block storageBlock;

	public RegisteredMetalOre(String id, float oreHardness, float oreResistance) {
		ore = Registry.register(Registries.BLOCK, Reforested.id(id + "_ore"), new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE).requiresTool().strength(oreHardness, oreResistance)));
		deepslateOre = Registry.register(Registries.BLOCK, Reforested.id("deepslate_" + id + "_ore"), new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_IRON_ORE).requiresTool().strength(oreHardness + 1.5f, oreResistance)));
		storageBlock = Registry.register(Registries.BLOCK, Reforested.id(id + "_block"), new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE).requiresTool().strength(oreHardness, oreResistance)));

		Registry.register(Registries.ITEM, Reforested.id(id + "_ore"), new BlockItem(ore, new FabricItemSettings()));
		Registry.register(Registries.ITEM, Reforested.id("deepslate_" + id + "_ore"), new BlockItem(deepslateOre, new FabricItemSettings()));
		Registry.register(Registries.ITEM, Reforested.id(id + "_block"), new BlockItem(storageBlock, new FabricItemSettings()));

		dust = Registry.register(Registries.ITEM, Reforested.id(id + "_dust"), new Item(new Item.Settings()));
		nugget = Registry.register(Registries.ITEM, Reforested.id(id + "_nugget"), new Item(new Item.Settings()));
		rawOre = Registry.register(Registries.ITEM, Reforested.id("raw_" + id), new Item(new Item.Settings()));

		Reforested.GROUP_ITEMS.add(new ItemStack(dust));
		Reforested.GROUP_ITEMS.add(new ItemStack(nugget));
		Reforested.GROUP_ITEMS.add(new ItemStack(rawOre));
	}
}
