package dev.prefex.reforested.machines.core;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public abstract class MachineBlockEntity extends BlockEntity {
    protected final PropertyDelegate propertyDelegate;
    protected DefaultedList<ItemStack> inventory;

    public MachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                              int inventorySize, PropertyDelegate propertyDelegate) {
        super(type, pos, state);
        this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
        this.propertyDelegate = propertyDelegate;
    }
}
