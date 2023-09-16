package dev.prefex.yokai.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import dev.prefex.yokai.slots.GhostSlot;

import static dev.prefex.reforested.Reforested.id;

public abstract class MachineScreen<T extends ScreenHandler> extends HandledScreen<T> {
	protected final Identifier background;
	protected final Identifier controlsBackground = id("textures/gui/controls_info.png");

	public MachineScreen(T handler, PlayerInventory inventory, Text title, Identifier background) {
		super(handler, inventory, title);
		this.background = background;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		context.drawTexture(this.background, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight, 256,256);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(context, mouseX, mouseY);
		for (Slot slot : handler.slots) {
			if (slot instanceof GhostSlot) {
				float[] defaultShader = RenderSystem.getShaderColor().clone();
				RenderSystem.setShaderColor(defaultShader[0], defaultShader[1], defaultShader[2], 0.5f);
				context.drawItem(((GhostSlot) slot).ghost, slot.x + x, slot.y + y);
				RenderSystem.setShaderColor(defaultShader[0], defaultShader[1], defaultShader[2], 1.0f);
			}
		}
	}
}
