package dev.prefex.reforested.machines.carpenter;

import dev.prefex.yokai.machine.MachineScreen;
import dev.prefex.reforested.machines.core.widgets.ButtonType;
import dev.prefex.reforested.machines.core.widgets.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterScreen extends MachineScreen<CarpenterScreenHandler> {

	public ButtonWidget lockButton;
	public ButtonWidget redstoneButton;

	public CarpenterScreen(CarpenterScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, id("textures/gui/carpenter.png"));
		this.height = 204;
		this.width = 168;
		this.backgroundWidth = 204;
		this.backgroundHeight = 168;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		super.drawBackground(context, delta, mouseX, mouseY);
		context.drawTexture(this.controlsBackground, x + backgroundWidth - 25, y, 72, 0, 24, 89, 176, 112);
	}

	@Override
	protected void init() {
		super.init();
		lockButton = new ButtonWidget(this.x + 65, this.y + 18, ButtonType.LOCK, Text.of("Lock recipe"));
		this.addDrawableChild(lockButton);

		redstoneButton = new ButtonWidget(this.x + 185, this.y + 6, ButtonType.REDSTONE, Text.of("Change redstone mode"));
		this.addDrawableChild(redstoneButton);
	}
}
