package dev.prefex.reforested.api;

import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;

public class Bee {
	public final String name;
	public final Color bodyColor;
	public final Color tailColor;
	public Climate climate;
	public Humidity humidity;
	public int temperatureTolerance;
	public int humidityTolerance;
	public float lifespan;
	public float speed;
	public float pollinationSpeed;
	public FlowerType flowers;
	public int fertility;
	public BeeWorkTime workTime;
	public Vec3d territory;

	public ArrayList<BeeModifier> modifiers;

	public Bee(String name, Color bodyColor, Color tailColor, Climate climate) {
		this.name = name;
		this.bodyColor = bodyColor;
		this.tailColor = tailColor;
		this.climate = climate;
	}

	public ArrayList<BeeProduct> getProducts() {
		return null; // Add json implementation
	}
}
