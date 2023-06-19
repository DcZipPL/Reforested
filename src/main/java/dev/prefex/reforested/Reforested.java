package dev.prefex.reforested;

import dev.prefex.reforested.blocks.ModBlocks;
import dev.prefex.reforested.entity.ModEntities;
import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.util.registry.RegisteredMetalOre;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Reforested implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("reforested");
	public static final String MOD_ID = "reforested";

	public static final ArrayList<ItemStack> GROUP_ITEMS = new ArrayList<>();
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static final ItemGroup REFORESTED_GROUP = FabricItemGroup.builder(id("reforested"))
			.icon(() -> new ItemStack(ModItems.BEE))
			.build();

	public static final ItemGroup EXTRA_BEES_GROUP = FabricItemGroup.builder(id("reforested_bees"))
			.icon(() -> new ItemStack(Items.BEE_NEST))
			.build();

	@Override
	public void onInitialize() {
		new RegisteredMetalOre("tin", 2.5F, 3.0F);

		ModEntities.init();
		ModItems.init();
		ModBlocks.init();

		ItemGroupEvents.modifyEntriesEvent(REFORESTED_GROUP).register(content -> {
			content.addAll(GROUP_ITEMS);
		});
	}
}
