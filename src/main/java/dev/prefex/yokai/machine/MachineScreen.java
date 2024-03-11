package dev.prefex.yokai.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prefex.yokai.client.widgets.EnergyWidget;
import dev.prefex.yokai.slots.GhostSlot;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.List;

import static dev.prefex.yokai.helpers.ColorBlender.blend;

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

	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		super.drawForeground(context, mouseX, mouseY);
		if (getEnergyWidget().isMouseOver(mouseX, mouseY))
			drawEnergyTooltip(context, mouseX, mouseY);
	}

	private void drawEnergyTooltip(DrawContext context, int mouseX, int mouseY) {
		context.drawTooltip(textRenderer, List.of(
				(Text) Text.translatable("tooltip.reforested.machine.energy_stored"),
				(Text) Text.literal(getEnergyString()+"/"+getMaxEnergyString()+" ⚡").setStyle(Style.EMPTY.withColor(Formatting.GRAY)),
				(Text) Text.literal(Math.round(getPowerPercentage()*10000)/100+"%")
						.setStyle(Style.EMPTY.withColor(getPowerPercentage() < 0.5f ?
								blend(new Color(0xff0000),new Color(0xffff00),getPowerPercentage()*2f).getRGB()
								: blend(new Color(0xffff00),new Color(0x00ff00),getPowerPercentage()*2f-1f).getRGB()
						))
		),mouseX + 4 - x,mouseY + 4 - y);
	}

	protected abstract String getEnergyString();
	protected abstract String getMaxEnergyString();
	protected abstract float getPowerPercentage();
	protected abstract EnergyWidget getEnergyWidget();

	protected static class Controls {
		public static void draw(DrawContext context, Identifier id, int x, int y, Rect2i controlRect) {
			context.drawTexture(id, x, y, controlRect.getX(), controlRect.getY(), controlRect.getWidth(), controlRect.getHeight());
		}

		public static final String background = "textures/gui/controls_info.png";

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
	}
}
