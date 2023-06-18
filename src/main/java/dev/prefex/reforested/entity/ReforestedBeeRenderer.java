package dev.prefex.reforested.entity;

import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.Identifier;

import static dev.prefex.reforested.Reforested.id;

public class ReforestedBeeRenderer extends BeeEntityRenderer {
	private final String name;

	public ReforestedBeeRenderer(EntityRendererFactory.Context context, String name) {
		super(context);
		this.name = name;
	}

	@Override
	public Identifier getTexture(BeeEntity beeEntity) {
		return id("textures/entity/bee/" + name + "_bee.png");
	}
}
