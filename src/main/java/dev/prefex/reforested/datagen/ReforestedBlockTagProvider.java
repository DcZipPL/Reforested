package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;

public class ReforestedBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ReforestedBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		try {
			configureMineable();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void configureMineable() throws IllegalAccessException {
		var scoopMineable = getOrCreateTagBuilder(ModTags.SCOOP_MINEABLE);
		var pickaxeMineable = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
		var axeMineable = getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);
		var shovelMineable = getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);

		scoopMineable.add(Blocks.BEE_NEST);

		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Mineable.class)) {
				Mineable mineable = field.getAnnotation(Mineable.class);
				switch (mineable.type()) {
					case SCOOP -> {
						scoopMineable.add((Block) field.get(null));
					}
					case PICKAXE -> {
						pickaxeMineable.add((Block) field.get(null));
					}
					case AXE -> {
						axeMineable.add((Block) field.get(null));
					}
					case SHOVEL -> {
						shovelMineable.add((Block) field.get(null));
					}
				}
			}
		}
	}
}
