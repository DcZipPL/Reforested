package dev.prefex.reforested.datagen;

import dev.prefex.reforested.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;

public class ReforestedBlockLootTables extends FabricBlockLootTableProvider {
	protected ReforestedBlockLootTables(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(Blocks.BEE_NEST, drops(ModItems.BEE));
	}
}
