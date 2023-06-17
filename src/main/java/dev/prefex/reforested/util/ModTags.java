package dev.prefex.reforested.util;

import dev.prefex.reforested.Reforested;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
	public static final TagKey<Block> SCOOP_MINEABLE = TagKey.of(RegistryKeys.BLOCK, Reforested.id("scoop_minable"));
}
