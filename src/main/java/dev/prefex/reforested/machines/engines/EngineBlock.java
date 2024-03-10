package dev.prefex.reforested.machines.engines;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

@SuppressWarnings("deprecation")
public abstract class EngineBlock extends BlockWithEntity {
	public static final DirectionProperty FACING = Properties.FACING;

	public EngineBlock(Settings settings) {
		super(settings.pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).allowsSpawning(Blocks::never).nonOpaque());
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.with(FACING, mirror.apply(state.get(FACING)));
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}
