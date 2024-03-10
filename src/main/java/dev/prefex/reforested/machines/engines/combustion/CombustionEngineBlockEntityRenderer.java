package dev.prefex.reforested.machines.engines.combustion;

import dev.prefex.yokai.client.FixRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CombustionEngineBlockEntityRenderer extends GeoBlockRenderer<CombustionEngineBlockEntity> {
	public CombustionEngineBlockEntityRenderer() {
		super(new GeoModel<CombustionEngineBlockEntity>() {
			@Override
			public Identifier getModelResource(CombustionEngineBlockEntity animatable) {
				return new Identifier("reforested", "geo/engine.geo.json");
			}

			@Override
			public Identifier getTextureResource(CombustionEngineBlockEntity animatable) {
				return new Identifier("reforested", "textures/block/combustion_engine.png");
			}

			@Override
			public Identifier getAnimationResource(CombustionEngineBlockEntity animatable) {
				return new Identifier("reforested", "animations/engine.animation.json");
			}
		});
	}

	@Override
	protected void rotateBlock(Direction facing, MatrixStack poseStack) {
		FixRenderer.rotateBlock(facing, poseStack);
	}
}
