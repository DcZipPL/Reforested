package dev.prefex.reforested.machines.core.widgets;

public enum ButtonType {
	LOCK(2),
	REDSTONE(3);

	public final int statesAmount;

	ButtonType(int statesAmount) {
		this.statesAmount = statesAmount;
	}
}
