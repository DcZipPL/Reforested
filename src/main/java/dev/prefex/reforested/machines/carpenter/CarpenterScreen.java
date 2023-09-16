package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.widgets.EnergyWidget;
import dev.prefex.yokai.machine.MachineScreen;
import dev.prefex.reforested.machines.core.widgets.ButtonType;
import dev.prefex.reforested.machines.core.widgets.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.List;

import static dev.prefex.reforested.Reforested.id;
import static dev.prefex.yokai.helpers.ColorBlender.blend;

public class CarpenterScreen extends MachineScreen<CarpenterScreenHandler> {
	private static final Rect2i PROGRESS = new Rect2i(176, 60, 4, 20);

	public ButtonWidget lockButton;
	public ButtonWidget redstoneButton;
	private EnergyWidget energyWidget;

	public CarpenterScreen(CarpenterScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, id("textures/gui/carpenter.png"));
		this.height = 176;
		this.width = 168;
		this.backgroundWidth = 176;
		this.backgroundHeight = 168;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		super.drawBackground(context, delta, mouseX, mouseY);
		Controls.draw(context, id(Controls.background), x - 28, y, Controls.RI_E);
		//Controls.draw(context, id(Controls.background), x - 22, y + 34, EnergyWidget.ENERGY_BAR_FULL);
		Controls.draw(context, background, x + 98, y + 52, PROGRESS);
		// TODO: Move this to the EnergyWidget
		if (energyWidget.isMouseOver(mouseX, mouseY))
			drawEnergyTooltip(context, mouseX, mouseY);
	}

	private void drawEnergyTooltip(DrawContext context, int mouseX, int mouseY) {
		float power_percent = (float) getScreenHandler().properties.get(2) / (float) getScreenHandler().properties.get(3);
		context.drawTooltip(textRenderer, List.of(
				(Text) Text.translatable("tooltip.reforested.machine.energy_stored"),
				(Text) Text.literal(getScreenHandler().getEnergyString()+"/"+getScreenHandler().getMaxEnergyString()+" ⚡").setStyle(Style.EMPTY.withColor(Formatting.GRAY)),
				(Text) Text.literal(Math.round(power_percent*10000)/100+"%")
						.setStyle(Style.EMPTY.withColor(power_percent < 0.5f ?
								blend(new Color(0xff0000),new Color(0xffff00),power_percent*2f).getRGB()
								: blend(new Color(0xffff00),new Color(0x00ff00),power_percent*2f-1f).getRGB()
						))
		),mouseX+4,mouseY+4);
	}

	@Override
	protected void init() {
		super.init();
		lockButton = new ButtonWidget(this.x + 65, this.y + 18, ButtonType.LOCK, Text.of("Lock recipe"));
		this.addDrawableChild(lockButton);

		redstoneButton = new ButtonWidget(this.x - 22, this.y + 6, ButtonType.REDSTONE, Text.of("Change redstone mode"));
		this.addDrawableChild(redstoneButton);

		energyWidget = new EnergyWidget(id(Controls.background), x - 22, y + 34);
		this.addDrawable(energyWidget);
	}
}
