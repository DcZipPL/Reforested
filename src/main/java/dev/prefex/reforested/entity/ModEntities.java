package dev.prefex.reforested.entity;

import dev.prefex.reforested.Reforested;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {

	public static final EntityType<BeeEntity> FOREST_BEE = registerBeeEntity("forest_bee");
	public static final EntityType<BeeEntity> MEADOW_BEE = registerBeeEntity("meadow_bee");

	public static EntityType<BeeEntity> registerBeeEntity(String name) {
		var self = Registry.register(Registries.ENTITY_TYPE,
				Reforested.id(name),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BeeEntity::new).dimensions(EntityDimensions.fixed(0.7F, 0.6F)).trackRangeChunks(8).build()
		);
		FabricDefaultAttributeRegistry.register(self, BeeEntity.createMobAttributes());

		return self;
	}

	public static void init() {}
}
