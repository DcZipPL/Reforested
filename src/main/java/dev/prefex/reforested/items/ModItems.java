package dev.prefex.reforested.items;

import dev.prefex.reforested.Reforested;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class ModItems {
	public static ArrayList<HoneycombItem> HONEYCOMBS = new ArrayList<>();
	public static ArrayList<PropolisItem> PROPOLISES = new ArrayList<>();

	public static final Item TIN_INGOT = register("tin_ingot");
	public static final Item BRONZE_INGOT = register("bronze_ingot");

	// Temporary Bee Item
	public static final Item BEE_DRONE = register("bee_drone");
	public static final Item BEE_QUEEN = register("bee_queen");
	public static final Item BEE_PRINCESS = register("bee_princess");
	public static final Item BUTTERFLY = register("butterfly");

	// Tools
	public static final Item SCOOP = register("scoop", new ScoopItem());
	public static final Item CAN = register("can");

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
	public static final HoneycombItem MUNDANE_HONEYCOMB = HoneycombItem.register("mundane");
	public static final HoneycombItem WATERY_HONEYCOMB = HoneycombItem.register("watery");

	// Propolises
	public static final PropolisItem PROPOLIS = PropolisItem.register();
	public static final PropolisItem PULSATING_PROPOLIS = PropolisItem.register("pulsating");
	public static final PropolisItem STICKY_PROPOLIS = PropolisItem.register("sticky");
	public static final PropolisItem SILKY_PROPOLIS = PropolisItem.register("silky");

	// Resources
	public static final Item HONEY_DROP = register("honey_drop");
	public static final Item HONEYDEW = register("honeydew");
	public static final Item CRYSTALLINE_POLLEN = register("crystalline_pollen");
	public static final Item POLLEN_CLUSTER = register("pollen_cluster");
	public static final Item ROYAL_JELLY = register("royal_jelly");
	public static final Item BEESWAX = register("beeswax");
	public static final Item REFRACTORY_WAX = register("refractory_wax");
	public static final Item SILK_WISP = register("silk_wisp");
	public static final Item PHOSPHOR = register("phosphor");
	public static final Item ICE_SHARD = register("ice_shard");

	public static final Item FERTILIZER = register("fertilizer");
	public static final Item ASH = register("ash");
	public static final Item PEAT = register("peat");
	public static final Item BITUMINOUS_PEAT = register("bituminous_peat");
	public static final Item COMPOST = register("compost");
	public static final Item MOULDY_WHEAT = register("mouldy_wheat");
	public static final Item DECAYED_WHEAT = register("decayed_wheat");
	public static final Item MULCH = register("mulch");

	public static final Item BASIC_CIRCUIT = register("basic_circuit");
	public static final Item GOOD_CIRCUIT = register("good_circuit");
	public static final Item ADVANCED_CIRCUIT = register("advanced_circuit");

	public static final Item BRONZE_GEAR = register("bronze_gear");
	public static final Item COPPER_GEAR = register("copper_gear");
	public static final Item TIN_GEAR = register("tin_gear");

	public static final Item FLEXIBLE_CASING = register("flexible_casing");
	public static final Item HARDENED_CASING = register("hardened_casing");
	public static final Item IMPREGNATED_CASING = register("impregnated_casing");
	public static final Item STURDY_CASING = register("sturdy_casing");

	public static final Item PULSATING_DUST = register("pulsating_dust");
	public static final Item PULSATING_MESH = register("pulsating_mesh");
	public static final Item IMPREGNATED_STICK = register("impregnated_stick");
	public static final Item SCENTED_PANELING = register("scented_paneling");
	public static final Item CAMOUFLAGE_PANELING = register("camouflage_paneling");
	public static final Item WOVEN_SILK = register("woven_silk");
	public static final Item WOOD_PULP = register("wood_pulp");


	// Register basic mod items
	public static Item register(String name) {
		return register(name, new Item(new Item.Settings()));
	}

	public static Item register(String name, Item item) {
		Item self = Registry.register(Registries.ITEM, Reforested.id(name), item);
		Reforested.GROUP_ITEMS.add(new ItemStack(self));
		return self;
	}

	public static void init() {
		// NO-OP - This is just here to make sure the class is loaded
	}
}
