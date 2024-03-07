package dev.prefex.reforested.items;

import net.minecraft.item.Item;

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
