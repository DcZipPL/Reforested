package dev.prefex.yokai.client.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class EnergyWidget implements Drawable, Widget {
	private final Identifier texture;
	private int x;
	private int y;
	private int progress;

	public static final Rect2i ENERGY_BAR = new Rect2i(0, 96, 12, 49);
	public static final Rect2i ENERGY_BAR_FULL = new Rect2i(12, 96, 12, 49);

	public EnergyWidget(Identifier texture, int x, int y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
	}

	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= (double)this.getX() && mouseY >= (double)this.getY() && mouseX < (double)(this.getX() + this.getWidth()) && mouseY < (double)(this.getY() + this.getHeight());
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		context.drawTexture(texture, this.getX(), this.getY(), ENERGY_BAR.getX(), ENERGY_BAR.getY(), ENERGY_BAR.getWidth(), ENERGY_BAR.getHeight(), 256, 256);
		context.drawTexture(texture, this.getX(), this.getY() + ENERGY_BAR.getHeight() - progress, ENERGY_BAR_FULL.getX(), ENERGY_BAR_FULL.getY() + ENERGY_BAR_FULL.getHeight() - progress, ENERGY_BAR_FULL.getWidth(), progress, 256, 256);
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getWidth() {
		return ENERGY_BAR.getWidth();
	}

	@Override
	public int getHeight() {
		return ENERGY_BAR.getHeight();
	}

	@Override
	public void forEachChild(Consumer<ClickableWidget> consumer) {

	}
}
