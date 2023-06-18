package dev.prefex.reforested;

import dev.prefex.reforested.entity.ModEntities;
import dev.prefex.reforested.entity.ReforestedBeeRenderer;
import dev.prefex.reforested.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.item.Item;

public class ReforestedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

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

		// Proplis
		registerColor(ModItems.PROPOLIS,				0xc5b24e);
		registerColor(ModItems.STICKY_PROPOLIS,			0xc68e57);
		registerColor(ModItems.PULSATING_PROPOLIS,		0x2ccdb1);
		registerColor(ModItems.SILKY_PROPOLIS,			0xddff00);

		EntityRendererRegistry.register(ModEntities.FOREST_BEE, ctx -> new ReforestedBeeRenderer(ctx, "forest_bee"));
		EntityRendererRegistry.register(ModEntities.MEADOW_BEE, ctx -> new ReforestedBeeRenderer(ctx, "meadow_bee"));
	}

	public static void registerColor(Item item, int... color) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintColorByIndex(tintIndex, color), item);
	}

	public static int tintColorByIndex(int tintIndex, int... color) {
		return color[tintIndex];
	}
}