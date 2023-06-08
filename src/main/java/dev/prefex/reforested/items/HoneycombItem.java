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
		HoneycombItem self = new HoneycombItem();
		Reforested.GROUP_ITEMS.add(new ItemStack(self));
		return (HoneycombItem) Registry.register(Registries.ITEM, Reforested.id(name + "_honeycomb"), self);
	}
}
