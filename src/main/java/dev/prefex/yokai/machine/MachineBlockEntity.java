package dev.prefex.yokai.machine;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;

public abstract class MachineBlockEntity extends LockableContainerBlockEntity implements ExtendedScreenHandlerFactory, SidedInventory {
    private final Identifier identifier;
    protected DefaultedList<ItemStack> inventory;

    public MachineBlockEntity(BlockEntityType<?> type, Identifier identifier, BlockPos pos, BlockState state, int inventorySize) {
        super(type, pos, state);
        this.identifier = identifier;
        this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    public abstract void onInventoryChanged(int slot, ItemStack stack);

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container."+identifier.getNamespace()+"."+identifier.getPath());
    }

    public boolean isEmpty() {
        Iterator<ItemStack> stacks = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!stacks.hasNext()) {
                return true;
            }

            itemStack = stacks.next();
        } while(itemStack.isEmpty());

        return false;
    }

    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        onInventoryChanged(slot, stack);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }
}
