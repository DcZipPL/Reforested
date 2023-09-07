package dev.prefex.reforested.machines.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
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
}
