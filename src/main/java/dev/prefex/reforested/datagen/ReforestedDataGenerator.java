package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.blocks.WipBlock;
import dev.prefex.reforested.util.WoodSet;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ReforestedDataGenerator implements DataGeneratorEntrypoint {
	public static final Logger LOGGER = LoggerFactory.getLogger("reforested");

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ReforestedModelGeneratorProvider::new);
		pack.addProvider(ReforestedEnglishLangProvider::new);
		pack.addProvider(ReforestedBlockTagProvider::new);
		pack.addProvider(ReforestedItemTagProvider::new);
		pack.addProvider(ReforestedBlockLootTables::new);
		pack.addProvider(ReforestedRecipeProvider::new);
	}

	public static void executeBeeNestFunction(Consumer<Block> block) {
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().contains("BEE_NEST")) {
				try {
					block.accept((Block) field.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void executeRegisterNotSkipped(Consumer<Block> block, ArrayList<Block> skipped) {
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			try {
				if (field.get(null) instanceof Block applyBlock) {
					if (!skipped.contains(applyBlock))
						block.accept(applyBlock);
				} else if (field.get(null) instanceof WoodSet applySet) {
					// TODO: Move this to other function
					// No skip needed yet
					block.accept(applySet.wood);
					block.accept(applySet.door);
					block.accept(applySet.fence);
					block.accept(applySet.fenceGate);
					block.accept(applySet.leaves);
					block.accept(applySet.log);
					block.accept(applySet.planks);
					block.accept(applySet.pressurePlate);
					block.accept(applySet.sapling);
					block.accept(applySet.sign);
					block.accept(applySet.slab);
					block.accept(applySet.stairs);
					block.accept(applySet.strippedLog);
					block.accept(applySet.strippedWood);
					block.accept(applySet.trapdoor);
					block.accept(applySet.hangingSign);
					block.accept(applySet.button);
					//block.accept(applySet.potted_sapling);
				}
			} catch (IllegalAccessException e) {
				LOGGER.error("Couldn't execute function:" + e);
				//throw new RuntimeException(e);
			}
		}
	}

	public static void executeWipBlockFunction(Consumer<Block> block) {
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().getName().equals(WipBlock.class.getName())) {
				try {
					block.accept((Block) field.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}