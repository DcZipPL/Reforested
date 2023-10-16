package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ReforestedBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ReforestedBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		var scoopMineable = getOrCreateTagBuilder(ModTags.SCOOP_MINEABLE);

		scoopMineable.add(Blocks.BEE_NEST);
		ReforestedDataGenerator.executeBeeNestFunction(scoopMineable::add);

		var shovelMineable = getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);

		shovelMineable.add(ModBlocks.BOG_EARTH);
	}
}
