package dev.prefex.reforested.machines.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class MachineScreenHandler extends ScreenHandler {
    private int nextIndex = 0;

    protected Inventory inventory;
    protected PropertyDelegate propertyDelegate;
    protected World world;
    protected BlockPos pos;

    public MachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, PacketByteBuf buf,
                                int inventorySize, int propertySize, int yOffset) {
        this(type, syncId, playerInventory, new SimpleInventory(inventorySize), new ArrayPropertyDelegate(propertySize), inventorySize, propertySize, yOffset);
        this.pos = buf.readBlockPos();
    }

    public MachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate,
                                int inventorySize, int propertySize, int yOffset) {
        super(type, syncId);
        checkSize(inventory, inventorySize);
        checkDataCount(propertyDelegate, propertySize);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.getWorld();

        this.pos = BlockPos.ORIGIN;

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, yOffset + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    protected void slot(int x, int y) {
        this.addSlot(new Slot(inventory, nextIndex++, x, y));
    }

    protected void ghostSlot(int x, int y) {
        this.addSlot(new Slot(inventory, nextIndex++, x, y));
    }
}
