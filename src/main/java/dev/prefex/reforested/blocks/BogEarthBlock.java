package dev.prefex.reforested.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

import java.util.Iterator;

public class BogEarthBlock extends Block {
	public static final BooleanProperty MATURE = BooleanProperty.of("mature");

	public BogEarthBlock(Settings settings) {
		super(settings.ticksRandomly());
		this.setDefaultState(this.stateManager.getDefaultState().with(MATURE, false));
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return !state.get(MATURE);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.get(MATURE)) {
			if (!isWaterNearby(world, pos)) return;
			if (random.nextInt(50) == 0) {
				world.setBlockState(pos, state.with(MATURE, true));
			}
		}
	}

	private static boolean isWaterNearby(WorldView world, BlockPos pos) {
		Iterator var2 = BlockPos.iterate(pos.add(-2, 0, -2), pos.add(2, 1, 2)).iterator();

		BlockPos blockPos;
		do {
			if (!var2.hasNext()) {
				return false;
			}

			blockPos = (BlockPos)var2.next();
		} while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

		return true;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(MATURE);
	}
}
