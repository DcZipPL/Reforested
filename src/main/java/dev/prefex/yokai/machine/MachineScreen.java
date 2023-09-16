package dev.prefex.yokai.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prefex.yokai.Vec2i;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import dev.prefex.yokai.slots.GhostSlot;
import net.minecraft.util.math.Vec2f;

import static dev.prefex.reforested.Reforested.id;

public abstract class MachineScreen<T extends ScreenHandler> extends HandledScreen<T> {
	protected final Identifier background;

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

	protected static class Controls {
		public static void drawControl(DrawContext context, int x, int y, Rect2i controlRect) {
			context.drawTexture(Controls.background, x, y, controlRect.getX(), controlRect.getY(), controlRect.getWidth(), controlRect.getHeight());
		}

		public static final Identifier background = id("textures/gui/controls_info.png");

		/**
		 * Redstone and Info control
		 */
		public static final Rect2i RI = new Rect2i(0, 0, 24, 38);
		/**
		 * Redstone, Day/Night, Info control
		 */
		public static final Rect2i RDNI = new Rect2i(24, 0, 24, 52);
		/**
		 * Redstone, Rain, Temperature, Info
		 */
		public static final Rect2i RRTI = new Rect2i(48, 0, 24, 66);
		/**
		 * Redstone, Info and Energy
		 */
		public static final Rect2i RI_E = new Rect2i(72, 0, 24, 89);

		public static final Rect2i ENERGY_BAR_FULL = new Rect2i(103, 52, 12, 49);
	}
}
