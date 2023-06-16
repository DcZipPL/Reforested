package dev.prefex.reforested.items;

import dev.prefex.reforested.Reforested;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class HoneycombItem extends Item {
	public HoneycombItem() {
		super(new Item.Settings());
	}

	public static HoneycombItem register(String name) {
		HoneycombItem self = (HoneycombItem) ModItems.register(name + "_honeycomb", new HoneycombItem());
		ModItems.HONEYCOMBS.add(self);
		return self;
	}
}
