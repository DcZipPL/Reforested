package dev.prefex.reforested.machines.engines.redstone;

import dev.prefex.reforested.machines.ModMachines;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class RedstoneEngineBlockEntity extends BlockEntity implements GeoBlockEntity {
	protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.PowerEngine.cold");
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(24, 0, 4);

	public int heat = 0;

	public RedstoneEngineBlockEntity(BlockPos pos, BlockState state) {
		super(ModMachines.REDSTONE_ENGINE_BLOCK_ENTITY, pos, state);
	}

	/**
	 * Frequency of energy generation per tick that depends on heat
	 * y=b-\left(\frac{x+1}{100}\right)
	 * @return Frequency in Hz(t)
	 */
	public float getFrequency() {
		return 28f - ((this.heat + 1f) / 100f);
	}

	@SuppressWarnings("UnstableApiUsage")
	public static void tick(World world, BlockPos pos, BlockState state, RedstoneEngineBlockEntity self) {
		if (world == null || world.isClient) {
			return;
		}
		if (self.energyStorage.capacity > self.energyStorage.amount) {
			if (world.getTime() % (int)self.getFrequency() == 0) {
				self.energyStorage.amount += 1;
			}
		}

		if (self.heat < 1000) {
			self.heat += 1;
		}

		for (Direction side : Direction.values()) {
			EnergyStorageUtil.move(
					self.energyStorage,
					EnergyStorage.SIDED.find(world, pos.offset(side), side.getOpposite()),
					Long.MAX_VALUE,
					null
			);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, this::deployAnimController));
	}

	protected <E extends RedstoneEngineBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
		return state.setAndContinue(IDLE);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
