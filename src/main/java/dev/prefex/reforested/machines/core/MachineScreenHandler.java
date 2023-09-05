package dev.prefex.reforested.machines.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class MachineScreenHandler extends ScreenHandler {
    protected Inventory inventory;
    protected PropertyDelegate propertyDelegate;
    protected World world;

    public MachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, int inventorySize, int propertySize) {
        this(type, syncId, playerInventory, new SimpleInventory(inventorySize), new ArrayPropertyDelegate(propertySize), inventorySize, propertySize);
    }

    public MachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate, int inventorySize, int propertySize) {
        super(type, syncId);
        checkSize(inventory, inventorySize);
        checkDataCount(propertyDelegate, propertySize);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.getWorld();

        this.addProperties(propertyDelegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
