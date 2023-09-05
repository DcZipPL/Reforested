package dev.prefex.reforested.machines.core;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public abstract class MachineScreen<T extends ScreenHandler> extends HandledScreen<T> {
	public MachineScreen(T handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}
}
