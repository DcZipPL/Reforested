package dev.prefex.reforested.machines.engines.stirling;

import dev.prefex.yokai.client.FixRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class StirlingEngineBlockEntityRenderer extends GeoBlockRenderer<StirlingEngineBlockEntity> {
	public StirlingEngineBlockEntityRenderer() {
		super(new GeoModel<StirlingEngineBlockEntity>() {
			@Override
			public Identifier getModelResource(StirlingEngineBlockEntity animatable) {
				return new Identifier("reforested", "geo/engine.geo.json");
			}

			@Override
			public Identifier getTextureResource(StirlingEngineBlockEntity animatable) {
				return new Identifier("reforested", "textures/block/stirling_engine.png");
			}

			@Override
			public Identifier getAnimationResource(StirlingEngineBlockEntity animatable) {
				return new Identifier("reforested", "animations/engine.animation.json");
			}
		});
	}

	@Override
	protected void rotateBlock(Direction facing, MatrixStack poseStack) {
		FixRenderer.rotateBlock(facing, poseStack);
	}
}
