package dev.prefex.reforested.datagen;

import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ReforestedBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ReforestedBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		var tagBuilder = getOrCreateTagBuilder(ModTags.SCOOP_MINEABLE);

		tagBuilder.add(Blocks.BEE_NEST);

		ReforestedDataGenerator.executeBeeNestFunction(tagBuilder::add);
	}
}
