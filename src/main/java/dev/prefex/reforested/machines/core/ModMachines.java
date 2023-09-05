package dev.prefex.reforested.machines.core;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.prefex.reforested.Reforested.id;

public class ModMachines {
	public static final BlockEntityType<CarpenterBlockEntity> CARPENTER_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			id("carpenter"),
			FabricBlockEntityTypeBuilder.create(CarpenterBlockEntity::new, ModBlocks.CARPENTER).build()
	);
}
