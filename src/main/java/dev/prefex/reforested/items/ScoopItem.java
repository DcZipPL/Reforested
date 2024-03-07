package dev.prefex.reforested.items;

import net.minecraft.item.*;

import static dev.prefex.reforested.util.ModTags.SCOOP_MINEABLE;

public class ScoopItem extends MiningToolItem {

	public ScoopItem() {
		super(1, -2.8F, ToolMaterials.STONE, SCOOP_MINEABLE, new Item.Settings().maxCount(1).maxDamage(64));
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return ingredient.getItem() == Items.STRING;
	}

	@Override
	public int getEnchantability() {
		return 15;
	}
}
