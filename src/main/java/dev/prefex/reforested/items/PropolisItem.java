package dev.prefex.reforested.items;

import net.minecraft.item.Item;

public class PropolisItem extends Item {
	public PropolisItem() {
		super(new Item.Settings());
	}

	public static PropolisItem register(String name) {
		PropolisItem self = (PropolisItem) ModItems.register(name + "_propolis", new PropolisItem());
		ModItems.PROPOLISES.add(self);
		return self;
	}

	public static PropolisItem register() {
		PropolisItem self = (PropolisItem) ModItems.register("propolis", new PropolisItem());
		ModItems.PROPOLISES.add(self);
		return self;
	}
}
