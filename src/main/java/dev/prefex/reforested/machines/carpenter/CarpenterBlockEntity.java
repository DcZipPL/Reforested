package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.ModMachines;
import dev.prefex.reforested.machines.core.WrappedDelegate;
import dev.prefex.reforested.machines.core.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class CarpenterBlockEntity extends MachineBlockEntity {
	public static final int INVENTORY_SIZE = 13;
	public static final int PROPERTY_SIZE = 2;
	public static final int Y_OFFSET = 92;

	int processTime;
	int maxProcessTime;
	public final PropertyDelegate properties;

    public CarpenterBlockEntity(BlockPos pos, BlockState state) {
        super(ModMachines.CARPENTER_BLOCK_ENTITY, pos, state, "carpenter", INVENTORY_SIZE);
		this.properties = WrappedDelegate.create(PROPERTY_SIZE, index -> switch (index) {
			case 0 -> CarpenterBlockEntity.this.processTime;
			case 1 -> CarpenterBlockEntity.this.maxProcessTime;
			default -> 0;
		}, pair -> {
			switch (pair.getLeft()) {
				case 0 -> CarpenterBlockEntity.this.processTime = pair.getRight();
				case 1 -> CarpenterBlockEntity.this.maxProcessTime = pair.getRight();
			}
		});
    }

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new CarpenterScreenHandler(syncId, playerInventory, this, this.properties);
	}

	/**
	 * Writes additional server -&gt; client screen opening data to the buffer.
	 *
	 * @param player the player that is opening the screen
	 * @param buf	the packet buffer
	 */
	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(pos);
	}

	@Override
	public void onInventoryChanged(int slot, ItemStack stack) {

	}

	@Override
	public boolean isValid(int slot, ItemStack stack) {
		return super.isValid(slot, stack);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return new int[0];
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return false;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return false;
	}
}