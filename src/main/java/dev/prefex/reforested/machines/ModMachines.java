package dev.prefex.reforested.machines;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity;
import dev.prefex.reforested.machines.carpenter.CarpenterRecipe;
import dev.prefex.reforested.machines.carpenter.CarpenterScreenHandler;
import dev.prefex.reforested.machines.engines.creative.CreativeEngineBlockEntity;
import dev.prefex.reforested.machines.engines.redstone.RedstoneEngineBlockEntity;
import dev.prefex.reforested.machines.engines.stirling.StirlingEngineBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

import static dev.prefex.reforested.Reforested.id;

public class ModMachines {
	public static final BlockEntityType<CarpenterBlockEntity> CARPENTER_BLOCK_ENTITY;
	public static final BlockEntityType<RedstoneEngineBlockEntity> REDSTONE_ENGINE_BLOCK_ENTITY;
	public static final BlockEntityType<StirlingEngineBlockEntity> STIRLING_ENGINE_BLOCK_ENTITY;
	public static final BlockEntityType<CreativeEngineBlockEntity> CREATIVE_ENGINE_BLOCK_ENTITY;

	public static final ExtendedScreenHandlerType<CarpenterScreenHandler> CARPENTER_SCREEN_HANDLER;


	static {
		CARPENTER_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				id("carpenter"),
				FabricBlockEntityTypeBuilder.create(CarpenterBlockEntity::new, ModBlocks.CARPENTER).build()
		);
		REDSTONE_ENGINE_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				id("redstone_engine"),
				FabricBlockEntityTypeBuilder.create(RedstoneEngineBlockEntity::new, ModBlocks.REDSTONE_ENGINE).build()
		);
		STIRLING_ENGINE_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				id("stirling_engine"),
				FabricBlockEntityTypeBuilder.create(StirlingEngineBlockEntity::new, ModBlocks.STIRLING_ENGINE).build()
		);
		CREATIVE_ENGINE_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				id("creative_engine"),
				FabricBlockEntityTypeBuilder.create(CreativeEngineBlockEntity::new, ModBlocks.CREATIVE_ENGINE).build()
		);

		CARPENTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, id("carpenter"), new ExtendedScreenHandlerType<>(CarpenterScreenHandler::new));
	}

	public static void init() {
		Registry.register(Registries.RECIPE_SERIALIZER, CarpenterRecipe.Type.ID, CarpenterRecipe.Serializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, CarpenterRecipe.Type.ID, CarpenterRecipe.Type.INSTANCE);

		EnergyStorage.SIDED.registerForBlockEntities((blockEntity, context) -> {
					if (blockEntity instanceof CarpenterBlockEntity be) return be.energyStorage;
					else return null;
				}, CARPENTER_BLOCK_ENTITY
		);

		EnergyStorage.SIDED.registerForBlockEntities((blockEntity, context) -> {
					if (blockEntity instanceof RedstoneEngineBlockEntity be) return be.energyStorage;
					else return null;
				}, REDSTONE_ENGINE_BLOCK_ENTITY
		);
	}
}
