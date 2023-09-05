package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.WrappedDelegate;
import dev.prefex.reforested.machines.core.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;

public class CarpenterBlockEntity extends MachineBlockEntity {
	int processTime;

	public PropertyDelegate properties = WrappedDelegate.create(1, index -> {
		switch (index) {
			case 0:
				return CarpenterBlockEntity.this.processTime;
			default:
				return 0;
		}
	}, pair -> {
		switch (pair.getLeft()) {
			case 0:
				CarpenterBlockEntity.this.processTime = pair.getRight();
				break;
		}
	});

    public CarpenterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 3, properties);
    }
}