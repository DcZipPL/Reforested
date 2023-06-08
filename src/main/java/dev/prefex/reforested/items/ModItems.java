package dev.prefex.reforested.items;

import dev.prefex.reforested.Reforested;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
	// Forestry Mod Honeycombs
	public static final HoneycombItem COCOA_HONEYCOMB = HoneycombItem.register("cocoa");
	public static final HoneycombItem DRIPPING_HONEYCOMB = HoneycombItem.register("dripping");
	public static final HoneycombItem FROZEN_HONEYCOMB = HoneycombItem.register("frozen");
	public static final HoneycombItem MELLOW_HONEYCOMB = HoneycombItem.register("mellow");
	public static final HoneycombItem MOSSY_HONEYCOMB = HoneycombItem.register("mossy");
	public static final HoneycombItem MYSTERIOUS_HONEYCOMB = HoneycombItem.register("mysterious");
	public static final HoneycombItem PARCHED_HONEYCOMB = HoneycombItem.register("parched");
	public static final HoneycombItem POWDERY_HONEYCOMB = HoneycombItem.register("powdery");
	public static final HoneycombItem SILKY_HONEYCOMB = HoneycombItem.register("silky");
	public static final HoneycombItem SIMMERING_HONEYCOMB = HoneycombItem.register("simmering");
	public static final HoneycombItem STRINGY_HONEYCOMB = HoneycombItem.register("stringy");
	public static final HoneycombItem WHEATEN_HONEYCOMB = HoneycombItem.register("wheaten");

	// Magic Bees Mod Honeycombs
	public static final HoneycombItem CARBON_HONEYCOMB = HoneycombItem.register("carbon");
	public static final HoneycombItem MUNDANE_HONEYCOMB = HoneycombItem.register("mundane");
	public static final HoneycombItem WATERY_HONEYCOMB = HoneycombItem.register("watery");

	public static final Item PROPOLIS = register("propolis");
	public static final Item PULSATING_PROPOLIS = register("pulsating_propolis");
	public static final Item STICKY_PROPOLIS = register("sticky_propolis");
	public static final Item SILKY_PROPOLIS = register("silky_propolis");

	public static final Item HONEY_DROP = register("honey_drop");
	public static final Item HONEYDEW = register("honeydew");
	public static final Item CRYSTALLINE_POLLEN = register("crystalline_pollen");
	public static final Item POLLEN_CLUSTER = register("pollen_cluster");
	public static final Item ROYAL_JELLY = register("royal_jelly");
	public static final Item BEEWAX = register("beewax");
	public static final Item REFACTORY_WAX = register("refactory_wax");
	public static final Item SILK_WISP = register("silk_wisp");
	public static final Item PHOSPHOR = register("phosphor");
	public static final Item ICE_SHARD = register("ice_shard");

	public static final Item

	// Register basic mod items
	public static Item register(String name) {
		Item self = new Item(new Item.Settings());
		Reforested.GROUP_ITEMS.add(new ItemStack(self));
		return Registry.register(Registries.ITEM, Reforested.id(name), self);
	}

	public static void init() {
		// NO-OP - This is just here to make sure the class is loaded
	}
}
