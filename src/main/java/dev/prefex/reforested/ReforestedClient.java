package dev.prefex.reforested;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.entity.ModEntities;
import dev.prefex.reforested.entity.ReforestedBeeRenderer;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.reforested.machines.carpenter.CarpenterScreen;
import dev.prefex.reforested.machines.engines.combustion.CombustionEngineBlockEntityRenderer;
import dev.prefex.reforested.machines.engines.redstone.RedstoneEngineBlockEntityRenderer;
import dev.prefex.reforested.machines.engines.stirling.StirlingEngineBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.item.Item;

public class ReforestedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.register(ModMachines.REDSTONE_ENGINE_BLOCK_ENTITY, (BlockEntityRendererFactory.Context model) -> new RedstoneEngineBlockEntityRenderer());
		BlockEntityRendererRegistry.register(ModMachines.STIRLING_ENGINE_BLOCK_ENTITY, (BlockEntityRendererFactory.Context model) -> new StirlingEngineBlockEntityRenderer());
		BlockEntityRendererRegistry.register(ModMachines.COMBUSTION_ENGINE_BLOCK_ENTITY, (BlockEntityRendererFactory.Context model) -> new CombustionEngineBlockEntityRenderer());

		HandledScreens.register(ModMachines.CARPENTER_SCREEN_HANDLER, CarpenterScreen::new);

		// Honeycombs
		registerColor(ModItems.COCOA_HONEYCOMB,			0xffb62b, 0x674016);
		registerColor(ModItems.SIMMERING_HONEYCOMB,		0xffb62b, 0x981919);
		registerColor(ModItems.STRINGY_HONEYCOMB,		0xbda93e, 0xc8be67);
		registerColor(ModItems.FROZEN_HONEYCOMB,		0xa0ffff, 0xf9ffff);
		registerColor(ModItems.DRIPPING_HONEYCOMB,		0xffff00, 0xdc7613);
		registerColor(ModItems.SILKY_HONEYCOMB,			0xddff00, 0x508907);
		registerColor(ModItems.PARCHED_HONEYCOMB,		0xffff00, 0xdcbe13);
		registerColor(ModItems.MYSTERIOUS_HONEYCOMB,	0xe099ff, 0x161616);
		//registerColor(ModItems.IRRADIATED_HONEYCOMB,		0xeeff00, 0xeafff3);
		registerColor(ModItems.POWDERY_HONEYCOMB,		0xffffff, 0x676767);
		//registerColor(ModItems.REDDENED_HONEYCOMB,		0x6200e7, 0x4b0000);
		//registerColor(ModItems.DARKENED_HONEYCOMB,		0x33ebcb, 0x353535);
		//registerColor(ModItems.OMEGA_HONEYCOMB,			0x6dcff6, 0x191919);
		registerColor(ModItems.WHEATEN_HONEYCOMB,		0xffffff, 0xfeff8f);
		registerColor(ModItems.MOSSY_HONEYCOMB,			0x7e9939, 0x2a3313);
		registerColor(ModItems.MELLOW_HONEYCOMB,		0xfff960, 0x886000);

		EntityRendererRegistry.register(ModEntities.FOREST_BEE, ctx -> new ReforestedBeeRenderer(ctx, "forest_bee"));
		EntityRendererRegistry.register(ModEntities.MEADOW_BEE, ctx -> new ReforestedBeeRenderer(ctx, "meadow_bee"));

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAIR.leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAIR.sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAIR.potted_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAIR.trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAIR.door, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN.leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN.sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN.potted_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN.trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN.door, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILLOW.leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILLOW.sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILLOW.potted_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILLOW.trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILLOW.door, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS.leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS.sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS.potted_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS.trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS.door, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUM_NUM.leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUM_NUM.sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUM_NUM.potted_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUM_NUM.trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUM_NUM.door, RenderLayer.getCutout());
	}

	public static void registerColor(Item item, int... color) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintColorByIndex(tintIndex, color), item);
	}

	public static int tintColorByIndex(int tintIndex, int... color) {
		return color[tintIndex];
	}
}