package dev.prefex.reforested.machines.core.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static dev.prefex.reforested.Reforested.id;

public class ButtonWidget extends ClickableWidget {
	private static final int TEXTURE_WIDTH = 60;
	private static final int TEXTURE_HEIGHT = 96;
	private static final int TEXTURE_SIZE = 12;
	private static final int RIPPLE_DURATION = 8;

	private float rippleTimer = 0;
	private int state = 0;

	protected ButtonType type;

	public ButtonWidget(int x, int y, @NotNull ButtonType type, Text message) {
		super(x, y, TEXTURE_SIZE, TEXTURE_SIZE, message);
		this.type = type;
	}

	@Override
	protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
		context.drawTexture(id("textures/gui/buttons.png"), this.getX(), this.getY(),
				getTextureU(),
				getTextureV(),
				TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_WIDTH, TEXTURE_HEIGHT
		);
		if (rippleTimer > 0) {
			rippleTimer = rippleTimer * delta;
		}
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		super.onClick(mouseX, mouseY);
		rippleTimer = RIPPLE_DURATION;
		state = (state + 1) % type.statesAmount;
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
		builder.put(NarrationPart.TITLE, this.getMessage());
	}

	private int getTextureU() {
		return (type.ordinal() == 0 ? 0 : type.ordinal() * TEXTURE_SIZE * 2 + TEXTURE_SIZE) + (rippleTimer > 0 ? TEXTURE_SIZE : 0);
	}

	private int getTextureV() {
		return this.state == 0 ? hoverTextureOffset() * TEXTURE_SIZE : (this.state * TEXTURE_SIZE) + (TEXTURE_SIZE * hoverTextureOffset());
	}

	private int hoverTextureOffset() {
		return rippleTimer > 0 ? 0 : (isHovered() ? type.statesAmount : 0);
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}
}
