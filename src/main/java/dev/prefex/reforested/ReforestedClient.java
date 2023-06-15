package dev.prefex.reforested;

import dev.prefex.reforested.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ReforestedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? 0x542f00 : 0xfcb80c, ModItems.COCOA_HONEYCOMB);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? 0x131313 : 0xb36ebf, ModItems.MYSTERIOUS_HONEYCOMB);
	}
}
