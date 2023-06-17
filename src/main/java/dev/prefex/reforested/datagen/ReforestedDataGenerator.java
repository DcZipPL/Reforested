package dev.prefex.reforested.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ReforestedDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ReforestedModelGeneratorProvider::new);
		pack.addProvider(ReforestedEnglishLangProvider::new);
		pack.addProvider(ReforestedBlockTagProvider::new);
		pack.addProvider(ReforestedBlockLootTables::new);
	}
}