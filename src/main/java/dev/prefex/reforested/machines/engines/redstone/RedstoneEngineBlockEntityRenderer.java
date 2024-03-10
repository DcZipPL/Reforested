package dev.prefex.reforested.machines.engines.redstone;

import dev.prefex.yokai.client.FixRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RedstoneEngineBlockEntityRenderer extends GeoBlockRenderer<RedstoneEngineBlockEntity> {
	public RedstoneEngineBlockEntityRenderer() {
		super(new GeoModel<RedstoneEngineBlockEntity>() {
			@Override
			public Identifier getModelResource(RedstoneEngineBlockEntity animatable) {
				return new Identifier("reforested", "geo/engine.geo.json");
			}

			@Override
			public Identifier getTextureResource(RedstoneEngineBlockEntity animatable) {
				return new Identifier("reforested", "textures/block/redstone_engine.png");
			}

			@Override
			public Identifier getAnimationResource(RedstoneEngineBlockEntity animatable) {
				return new Identifier("reforested", "animations/engine.animation.json");
			}
		});
	}

	@Override
	protected void rotateBlock(Direction facing, MatrixStack poseStack) {
		FixRenderer.rotateBlock(facing, poseStack);
	}
}
