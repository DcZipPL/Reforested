package dev.prefex.reforested.api;

public enum Humidity {
	Arid(0.0f),
	Normal(0.3f),
	Damp(0.9f);

	public final float percentage;

	Humidity(float percentage) {
		this.percentage = percentage;
	}
}
