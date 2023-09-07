package dev.prefex.reforested.machines.core;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class MachineScreen<T extends ScreenHandler> extends HandledScreen<T> {
	protected final Identifier background;

	public MachineScreen(T handler, PlayerInventory inventory, Text title, Identifier background) {
		super(handler, inventory, title);
		this.background = background;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		context.drawTexture(this.background, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		for (Slot slot : handler.slots) {
			if (slot instanceof GhostSlot) {
				float[] defaultShader = RenderSystem.getShaderColor();
				RenderSystem.setShaderColor(defaultShader[0], defaultShader[1], defaultShader[2], 0.5f);
				context.drawItem(((GhostSlot) slot).ghost, slot.x, slot.y + 10);
				RenderSystem.setShaderColor(defaultShader[0], defaultShader[1], defaultShader[2], defaultShader[3]);
			}
		}
	}
}
