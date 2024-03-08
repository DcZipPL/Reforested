package dev.prefex.reforested.datagen;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.items.HoneycombItem;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.util.WoodSet;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Optional;

import static dev.prefex.reforested.Reforested.id;

public class ReforestedModelGeneratorProvider extends FabricModelProvider {

	ReforestedModelGeneratorProvider(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ReforestedDataGenerator.executeBeeNestFunction(block -> {
			blockStateModelGenerator.registerBeehive(block, this::beeNestMap);
		});

		createMachineModel(ModBlocks.CARPENTER, blockStateModelGenerator);
		createMachineModel(ModBlocks.SQUEEZER, blockStateModelGenerator);
		createMachineModel(ModBlocks.CENTRIFUGE, blockStateModelGenerator);
		createMachineModel(ModBlocks.STILL, blockStateModelGenerator);
		createMachineModel(ModBlocks.FERMENTER, blockStateModelGenerator);
		createMachineModel(ModBlocks.MOISTENER, blockStateModelGenerator);
		createMachineModel(ModBlocks.BOTTLER, blockStateModelGenerator);

		createEngineBlockModel(ModBlocks.REDSTONE_ENGINE, blockStateModelGenerator);

		ReforestedDataGenerator.executeWipBlockFunction(blockStateModelGenerator::registerSimpleCubeAll);

		// Register all wood set blocks
		Class<ModBlocks> blocksClass = ModBlocks.class;
		Field[] fields = blocksClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().getName().equals(WoodSet.class.getName())) {
				try {
					WoodSet woodSet = (WoodSet) field.get(null);
					blockStateModelGenerator.registerFlowerPotPlant(woodSet.sapling, woodSet.potted_sapling, BlockStateModelGenerator.TintType.NOT_TINTED);
					blockStateModelGenerator.registerLog(woodSet.log).log(woodSet.log).wood(woodSet.wood);
					blockStateModelGenerator.registerLog(woodSet.strippedLog).log(woodSet.strippedLog).wood(woodSet.strippedWood);
					blockStateModelGenerator.registerSimpleCubeAll(woodSet.planks);
					blockStateModelGenerator.registerSimpleCubeAll(woodSet.leaves);
					blockStateModelGenerator.registerTrapdoor(woodSet.trapdoor);
					blockStateModelGenerator.registerDoor(woodSet.door);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private void createEngineBlockModel(Block block, BlockStateModelGenerator blockStateModelGenerator) {
		//blockStateModelGenerator.registerRod(block);
	}

	private void createMachineModel(Block block, BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSingleton(block,
				TexturedModel.makeFactory((b) -> {
					return TextureMap.of(TextureKey.of("0"), id("block/carpenter"));
				}, new Model(Optional.of(id("block/basic_machine")), Optional.empty()))
		);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (var comb : ModItems.HONEYCOMBS) {
			itemModelGenerator.register(comb, new Model(Optional.of(id("item/honeycomb")), Optional.empty()));
		}
		for (var item : Reforested.GROUP_ITEMS) {
			if (item.getItem() instanceof HoneycombItem) continue;
			if (item.getItem() instanceof BlockItem) continue;

			ReforestedDataGenerator.LOGGER.info("Registering item model for " + item.getItem().getTranslationKey());
			itemModelGenerator.register(item.getItem(), Models.GENERATED);
		}
	}

	public final void registerSimpleMachine(Block block, TexturedModel.Factory factory, BlockStateModelGenerator blockStateModelGenerator) {
		/*Identifier identifier = */ factory.upload(block, blockStateModelGenerator.modelCollector);
		//Identifier identifier2 = TextureMap.getSubId(cooker, "_front_on");
		/*modelFactory.get(cooker).textures((textures) -> {
			textures.put(TextureKey.FRONT, identifier2);
		}).upload(cooker, "_on", blockStateModelGenerator.modelCollector);*/
		//blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(createNorthDefaultHorizontalRotationStates()));
	}

	private static Identifier getBeeNestId(Block block, String suffix) {
		Identifier identifier = Registries.BLOCK.getId(block);
		return identifier.withPath((path) -> "block/bee_nest/" + path + suffix);
	}

	private TextureMap beeNestMap(Block block) {
		return (new TextureMap()).put(TextureKey.SIDE, getBeeNestId(block, "_side")).put(TextureKey.FRONT, getBeeNestId(block, "_front")).put(TextureKey.TOP, getBeeNestId(block, "_top")).put(TextureKey.BOTTOM, getBeeNestId(block, "_bottom"));
	}
}