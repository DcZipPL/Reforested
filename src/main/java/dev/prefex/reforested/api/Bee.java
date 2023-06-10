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

	public Bee(String name, Color bodyColor, Color tailColor, Climate climate, Humidity humidity, int temperatureTolerance, int humidityTolerance, float lifespan, float speed, float pollinationSpeed, FlowerType flowers, int fertility, BeeWorkTime workTime, Vec3d territory, ArrayList<BeeModifier> modifiers) {
		this.name = name;
		this.bodyColor = bodyColor;
		this.tailColor = tailColor;
		this.climate = climate;
		this.humidity = humidity;
		this.temperatureTolerance = temperatureTolerance;
		this.humidityTolerance = humidityTolerance;
		this.lifespan = lifespan;
		this.speed = speed;
		this.pollinationSpeed = pollinationSpeed;
		this.flowers = flowers;
		this.fertility = fertility;
		this.workTime = workTime;
		this.territory = territory;
		this.modifiers = modifiers;
	}

	public ArrayList<BeeProduct> getProducts() {
		return null; // Add json implementation
	}
}
