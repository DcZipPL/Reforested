package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.Reforested;
import dev.prefex.yokai.machine.MachineScreenHandler;
import dev.prefex.reforested.machines.ModMachines;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;

import static dev.prefex.reforested.machines.carpenter.CarpenterBlockEntity.*;

public class CarpenterScreenHandler extends MachineScreenHandler {

	public CarpenterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		super(ModMachines.CARPENTER_SCREEN_HANDLER, syncId, playerInventory, buf, INVENTORY_SIZE, PROPERTY_SIZE, Y_OFFSET);
	}
	protected CarpenterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(ModMachines.CARPENTER_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate, INVENTORY_SIZE, PROPERTY_SIZE, Y_OFFSET);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int slot) {
		Reforested.LOGGER.error("Quick move not implemented!");
		return ItemStack.EMPTY;
	}

	@Override
	public void createSlots() {
		// Crafting grid
		makeGhostGrid(0, 10, 19, 3,3);
		skipSlots(9);

		// Input slot
		slot(80, 19);

		// Process slots
		intermediateSlot(80, 54);
		resultSlot(116, 55);

		// Fluid slots
		slot(134, 13);
		resultSlot(134, 55);
	}

	public String getEnergyString() {
		StringBuilder energy = new StringBuilder(String.valueOf(properties.get(2) / 8.0F));
		// Split the string at the decimal point
		String[] split = energy.toString().split("\\.");
		// If the string is less than 3 characters long, add a 0 to the end
		for (int i = 0; i < 3 - split[1].length(); i++) {
			energy.append("0");
		}

		return energy.toString();
	}

	public String getMaxEnergyString() {
		StringBuilder energy = new StringBuilder(String.valueOf(properties.get(3) / 8.0F));
		// Split the string at the decimal point
		String[] split = energy.toString().split("\\.");
		// If the string is less than 3 characters long, add a 0 to the end
		for (int i = 0; i < 3 - split[1].length(); i++) {
			energy.append("0");
		}
		return energy.toString();
	}
}
