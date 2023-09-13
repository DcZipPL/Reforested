package dev.prefex.reforested.util;

import dev.prefex.reforested.Reforested;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

	private static Identifier common(String s) {
		return new Identifier("c", s);
	}

	public static final TagKey<Item> GLASS = TagKey.of(RegistryKeys.ITEM, common("glass"));

	public static final TagKey<Item> INGOTS = TagKey.of(RegistryKeys.ITEM, common("ingots"));
	public static final TagKey<Item> GEARS = TagKey.of(RegistryKeys.ITEM, common("gears"));

	public static final TagKey<Item> COPPER_GEARS = TagKey.of(RegistryKeys.ITEM, common("copper_gears"));
	public static final TagKey<Item> TIN_GEARS = TagKey.of(RegistryKeys.ITEM, common("tin_gears"));
	public static final TagKey<Item> BRONZE_GEARS = TagKey.of(RegistryKeys.ITEM, common("bronze_gears"));

	public static final TagKey<Item> TIN_INGOTS = TagKey.of(RegistryKeys.ITEM, common("tin_ingots"));
	public static final TagKey<Item> BRONZE_INGOTS = TagKey.of(RegistryKeys.ITEM, common("bronze_ingots"));

	public static final TagKey<Block> SCOOP_MINEABLE = TagKey.of(RegistryKeys.BLOCK, Reforested.id("scoop_minable"));
}
