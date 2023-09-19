package dev.prefex.reforested.machines.engines.creative;

import dev.prefex.reforested.machines.ModMachines;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CreativeEngineBlockEntity extends BlockEntity {
	public CreativeEngineBlockEntity(BlockPos pos, BlockState state) {
		super(ModMachines.CREATIVE_ENGINE_BLOCK_ENTITY, pos, state);
	}
}
