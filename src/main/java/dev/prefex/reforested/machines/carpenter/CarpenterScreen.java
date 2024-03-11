package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.core.widgets.ButtonType;
import dev.prefex.reforested.machines.core.widgets.ButtonWidget;
import dev.prefex.yokai.client.widgets.EnergyWidget;
import dev.prefex.yokai.machine.MachineScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import static dev.prefex.reforested.Reforested.id;
import static dev.prefex.yokai.client.widgets.EnergyWidget.ENERGY_BAR;

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
		Controls.draw(context, background, x + 98, y + 52, PROGRESS);
	}

	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		super.drawForeground(context, mouseX, mouseY);
		energyWidget.setProgress((int)((float)getScreenHandler().properties.get(2)/(float)getScreenHandler().properties.get(3)*ENERGY_BAR.getHeight()));
	}

	@Override
	protected String getEnergyString() {
		return getScreenHandler().getEnergyString();
	}

	@Override
	protected String getMaxEnergyString() {
		return getScreenHandler().getMaxEnergyString();
	}

	@Override
	protected float getPowerPercentage() {
		return (float) getScreenHandler().properties.get(2) / (float) getScreenHandler().properties.get(3);
	}

	@Override
	protected EnergyWidget getEnergyWidget() {
		return energyWidget;
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
