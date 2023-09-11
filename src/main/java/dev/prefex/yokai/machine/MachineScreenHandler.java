package dev.prefex.yokai.machine;

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

import dev.prefex.yokai.GhostSlot;

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
        //checkSize(inventory, inventorySize);
        //checkDataCount(propertyDelegate, propertySize);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.getWorld();

        this.pos = BlockPos.ORIGIN;

        createSlots();

        makeGrid(playerInventory, 9, 8, yOffset, 9, 3);
        makeGrid(playerInventory, 0, 8, yOffset + 58, 9, 1);

        this.addProperties(propertyDelegate);
    }

    public abstract void createSlots();

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    protected void slot(int x, int y) {
        this.addSlot(new Slot(inventory, nextIndex++, x, y));
    }

    protected void ghostSlot(int x, int y) {
        this.addSlot(new GhostSlot(inventory, nextIndex++, x, y));
    }

    protected void makeGrid(Inventory inventory, int startIndex, int x, int y, int xSize, int ySize) {
        for(int i = 0; i < ySize; ++i) {
            for(int j = 0; j < xSize; ++j) {
                this.addSlot(new Slot(inventory, j + i * xSize + startIndex, x + j * 18, y + i * 18));
            }
        }
    }

    protected void makeGhostGrid(int startIndex, int x, int y, int xSize, int ySize) {
        for(int i = 0; i < ySize; ++i) {
            for(int j = 0; j < xSize; ++j) {
                this.addSlot(new GhostSlot(inventory, j + i * xSize + startIndex, x + j * 18, y + i * 18));
            }
        }
    }
}