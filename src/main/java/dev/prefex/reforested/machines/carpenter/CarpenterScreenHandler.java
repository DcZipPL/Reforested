package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.MachineScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

import static dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity.INVENTORY_SIZE;
import static dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity.PROPERTY_SIZE;

public class CarpenterScreenHandler extends MachineScreenHandler {
	protected CarpenterScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(type, syncId, playerInventory, inventory, propertyDelegate, INVENTORY_SIZE, PROPERTY_SIZE);
	}

	protected CarpenterScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
		super(type, syncId, playerInventory, INVENTORY_SIZE, PROPERTY_SIZE);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int slot) {
		return null;
	}
}
