package dev.prefex.reforested.api;

public enum Climate {
	Glacial(0.0f),
	Icy(0.05f),
	Cold(0.2f),
	Temperate(0.6f),
	Warm(1.2f),
	Hot(2.0f),
	Scorching(2.5f),
	Hellish(3.0f);

	public final float percentage;

	Climate(float percentage) {
		this.percentage = percentage;
	}
}
