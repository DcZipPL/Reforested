package dev.prefex.reforested.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}