package dev.prefex.reforested.machines.core.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class EnergyWidget implements Drawable, Widget {
	private final Identifier texture;
	private int x;
	private int y;

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
