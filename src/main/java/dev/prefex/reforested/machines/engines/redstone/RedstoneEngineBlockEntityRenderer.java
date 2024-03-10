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
				if (animatable.heat < (RedstoneEngineBlockEntity.MAX_HEAT / 10f) * 2.5)
					return new Identifier("reforested", "textures/block/engine/redstone/cold.png");
				else if (animatable.heat < (RedstoneEngineBlockEntity.MAX_HEAT / 10f) * 5)
					return new Identifier("reforested", "textures/block/engine/redstone/temperate.png");
				else if (animatable.heat < (RedstoneEngineBlockEntity.MAX_HEAT / 10f) * 7.5)
					return new Identifier("reforested", "textures/block/engine/redstone/warm.png");
				else if (animatable.heat <= RedstoneEngineBlockEntity.MAX_HEAT)
					return new Identifier("reforested", "textures/block/engine/redstone/hot.png");
				else
					return new Identifier("reforested", "textures/block/engine/redstone/max.png");
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
