package dev.prefex.reforested.machines.engines.stirling;

import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.reforested.machines.engines.GeoEngine;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StirlingEngineBlockEntity extends BlockEntity implements GeoEngine<StirlingEngineBlockEntity> {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public StirlingEngineBlockEntity(BlockPos pos, BlockState state) {
		super(ModMachines.STIRLING_ENGINE_BLOCK_ENTITY, pos, state);
	}

	@Override
	public PlayState animController(AnimationState<GeoEngine<StirlingEngineBlockEntity>> state) {
		return state.setAndContinue(WARM);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	public static void tick(World world, BlockPos blockPos, BlockState blockState, StirlingEngineBlockEntity self) {

	}
}
