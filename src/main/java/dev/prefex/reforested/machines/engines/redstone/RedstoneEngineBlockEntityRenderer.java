package dev.prefex.reforested.machines.engines.redstone;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
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
		switch (facing) {
			case SOUTH -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
			case WEST -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
			case NORTH -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0));
			case EAST -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270));
			case UP -> {
				poseStack.translate(0, 0.5, -0.5);
				poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
			}
			case DOWN -> {
				poseStack.translate(0, 0.5, 0.5);
				poseStack.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(90));
			}
		}
	}
}
