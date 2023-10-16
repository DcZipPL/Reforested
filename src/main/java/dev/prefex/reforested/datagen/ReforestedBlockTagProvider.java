package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.util.ModTags;
import dev.prefex.reforested.util.WoodSet;
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
		configureWoodSet();

		try {
			configureMineable();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void configureWoodSet() {
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			try {
				if (field.get(null) instanceof WoodSet applySet) {
					getOrCreateTagBuilder(applySet.logTag).add(applySet.log);
					getOrCreateTagBuilder(applySet.logTag).add(applySet.wood);
					getOrCreateTagBuilder(applySet.logTag).add(applySet.strippedLog);
					getOrCreateTagBuilder(applySet.logTag).add(applySet.strippedWood);
					getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(applySet.log); // Add set of logs from above

					getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(applySet.door);
					getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(applySet.fence);
					getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(applySet.fenceGate);
					getOrCreateTagBuilder(BlockTags.LEAVES).add(applySet.leaves);
					getOrCreateTagBuilder(BlockTags.PLANKS).add(applySet.planks);
					getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(applySet.pressurePlate);
					getOrCreateTagBuilder(BlockTags.SAPLINGS).add(applySet.sapling);
					//getOrCreateTagBuilder(BlockTags.SIGns).add(applySet.sign);
					getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(applySet.slab);
					getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(applySet.stairs);
					getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(applySet.trapdoor);
					//getOrCreateTagBuilder(BlockTags.WOOD).add(applySet.hangingSign);
					getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(applySet.button);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void configureMineable() throws IllegalAccessException {
		var scoopMineable = getOrCreateTagBuilder(ModTags.SCOOP_MINEABLE);
		var pickaxeMineable = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
		var axeMineable = getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);
		var shovelMineable = getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);

		scoopMineable.add(Blocks.BEE_NEST);

		ReforestedDataGenerator.executeWoodSet(axeMineable::add, __ -> {});

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
