package dev.prefex.reforested.machines.engines;

import net.minecraft.block.entity.BlockEntity;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public interface GeoEngine<E extends BlockEntity> extends GeoBlockEntity {
	RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.PowerEngine.idle");
	RawAnimation COLD = RawAnimation.begin().thenLoop("animation.PowerEngine.cold");
	RawAnimation TEMPERATE = RawAnimation.begin().thenLoop("animation.PowerEngine.temperate");
	RawAnimation WARM = RawAnimation.begin().thenLoop("animation.PowerEngine.warm");
	RawAnimation HOT = RawAnimation.begin().thenLoop("animation.PowerEngine.hot");
	RawAnimation MAX = RawAnimation.begin().thenLoop("animation.PowerEngine.max");

	@Override
	default void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, (this::animController)));
	}

	PlayState animController(final AnimationState<GeoEngine<E>> state);
}
