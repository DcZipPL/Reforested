package dev.prefex.reforested.api;

import net.minecraft.item.ItemStack;

public record BeeProduct(ItemStack item, float chance, boolean isSpecial) {
	public BeeProduct(ItemStack item, float chance) {
		this(item, chance, false);
	}
}
