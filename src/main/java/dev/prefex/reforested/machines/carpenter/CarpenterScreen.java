package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.MachineScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterScreen extends MachineScreen<CarpenterScreenHandler> {

	public CarpenterScreen(CarpenterScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, id("textures/gui/carpenter.png"));
		this.height = 176;
		this.width = 172;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		super.drawBackground(context, delta, mouseX, mouseY);
	}
}
