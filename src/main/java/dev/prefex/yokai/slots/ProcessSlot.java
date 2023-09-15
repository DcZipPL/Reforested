package dev.prefex.yokai.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ProcessSlot extends Slot {
	public ProcessSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canTakeItems(PlayerEntity playerEntity) {
		return false;
	}
}
