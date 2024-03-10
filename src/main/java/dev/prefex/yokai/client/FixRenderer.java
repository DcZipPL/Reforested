package dev.prefex.yokai.client;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public final class FixRenderer {
	public static void rotateBlock(Direction facing, MatrixStack poseStack) {
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
