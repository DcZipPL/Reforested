package dev.prefex.reforested.machines.carpenter;

import com.mojang.serialization.MapCodec;
import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.yokai.machine.MachineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarpenterBlock extends MachineBlock {
	public CarpenterBlock(Settings settings) {
		super(settings.nonOpaque());
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CarpenterBlockEntity(pos, state);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof CarpenterBlockEntity) {
				ItemScatterer.spawn(world, pos, (CarpenterBlockEntity)blockEntity);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, ModMachines.CARPENTER_BLOCK_ENTITY, CarpenterBlockEntity::tick);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return createCodec(CarpenterBlock::new);
	}
}
