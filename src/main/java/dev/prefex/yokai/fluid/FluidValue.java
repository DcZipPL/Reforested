package dev.prefex.yokai.fluid;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.util.JsonHelper;

import java.util.Objects;

/*
 * This file was part of RebornCore, licensed under the MIT License (MIT). Made by TeamReborn.
 */

public final class FluidValue {

	public static final FluidValue EMPTY = new FluidValue(0);
	public static final FluidValue BOTTLE = new FluidValue(FluidConstants.BUCKET / 4);
	public static final FluidValue BUCKET = new FluidValue(FluidConstants.BUCKET);

	private final long rawValue;

	public static FluidValue fromMillibuckets(long millibuckets) {
		return new FluidValue(millibuckets * 81);
	}

	private FluidValue(long rawValue) {
		this.rawValue = rawValue;
	}

	public FluidValue multiply(long value) {
		return fromRaw(rawValue * value);
	}

	public FluidValue fraction(long divider) {return fromRaw(rawValue / divider);}

	public FluidValue add(FluidValue fluidValue) {
		return fromRaw(rawValue + fluidValue.rawValue);
	}

	public FluidValue subtract(FluidValue fluidValue) {
		return fromRaw(rawValue - fluidValue.rawValue);
	}

	public FluidValue min(FluidValue fluidValue) {
		return fromRaw(Math.min(rawValue, fluidValue.rawValue));
	}

	public boolean isEmpty() {
		return rawValue == 0;
	}

	public boolean moreThan(FluidValue value) {
		return rawValue > value.rawValue;
	}

	public boolean equalOrMoreThan(FluidValue value) {
		return rawValue >= value.rawValue;
	}

	public boolean lessThan(FluidValue value) {
		return rawValue < value.rawValue;
	}

	public boolean lessThanOrEqual(FluidValue value) {
		return rawValue <= value.rawValue;
	}

	@Override
	public String toString() {
		return FluidTextHelper.getValueDisplay(this) + " m\uD83E\uDEA3";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FluidValue that = (FluidValue) o;
		return rawValue == that.rawValue;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(rawValue);
	}

	public long getRawValue() {
		return rawValue;
	}

	public static FluidValue fromRaw(long rawValue) {
		if (rawValue < 0) {
			rawValue = 0;
		}
		return new FluidValue(rawValue);
	}

	public static FluidValue parseFluidValue(JsonElement jsonElement) {
		if (jsonElement.isJsonObject()) {
			final JsonObject jsonObject = jsonElement.getAsJsonObject();
			if (jsonObject.has("buckets")) {
				int buckets = JsonHelper.getInt(jsonObject, "buckets");
				return BUCKET.multiply(buckets);
			} else if (jsonObject.has("droplets")) {
				long droplets = JsonHelper.getLong(jsonObject, "droplets");
				return fromRaw(droplets);
			}
		} else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
			return fromMillibuckets(jsonElement.getAsJsonPrimitive().getAsInt());
		}
		throw new JsonSyntaxException("Could not parse fluid value");
	}
}