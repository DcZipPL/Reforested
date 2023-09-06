package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.MachineScreenHandler;
import dev.prefex.reforested.machines.core.ModMachines;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;

import static dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity.INVENTORY_SIZE;
import static dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity.PROPERTY_SIZE;

public class CarpenterScreenHandler extends MachineScreenHandler {
	protected CarpenterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(ModMachines.CARPENTER_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate, INVENTORY_SIZE, PROPERTY_SIZE);
	}

	public CarpenterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		super(ModMachines.CARPENTER_SCREEN_HANDLER, syncId, playerInventory, INVENTORY_SIZE, PROPERTY_SIZE, buf);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int slot) {
		return null;
	}
}
