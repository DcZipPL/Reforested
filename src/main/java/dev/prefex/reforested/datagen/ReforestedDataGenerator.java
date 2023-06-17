package dev.prefex.reforested.datagen;

import dev.prefex.reforested.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public class ReforestedDataGenerator implements DataGeneratorEntrypoint {
	public static final Logger LOGGER = LoggerFactory.getLogger("reforested");
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ReforestedModelGeneratorProvider::new);
		pack.addProvider(ReforestedEnglishLangProvider::new);
		pack.addProvider(ReforestedBlockTagProvider::new);
		pack.addProvider(ReforestedBlockLootTables::new);
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
}