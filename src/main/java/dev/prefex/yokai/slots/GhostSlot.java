package dev.prefex.yokai.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class GhostSlot extends Slot {
	public boolean locked = false;
	public ItemStack ghost = ItemStack.EMPTY;

	public GhostSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public ItemStack insertStack(ItemStack stack, int count) {
		this.ghost = stack.copyWithCount(1);
		return super.insertStack(stack, count);
	}

	@Override
	public ItemStack takeStack(int amount) {
		if (!locked)
			ghost = ItemStack.EMPTY;
		return super.takeStack(amount);
	}
}
