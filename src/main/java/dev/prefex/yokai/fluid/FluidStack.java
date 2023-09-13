package dev.prefex.yokai.fluid;

import dev.onyxstudios.cca.api.v3.util.NbtSerializable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class FluidStack implements NbtSerializable {
	public static final String FLUID_KEY = "Fluid";
	public static final String AMOUNT_KEY = "Amount";
	public static final String TAG_KEY = "Tag";

	public static final FluidStack EMPTY = new FluidStack(FluidVariant.blank(), FluidValue.EMPTY);

	protected FluidVariant fluid;
	protected FluidValue amount;

	public FluidStack(FluidVariant fluid, FluidValue amount) {
		this.fluid = fluid;
		this.amount = amount;
	}

	public FluidStack(FluidVariant fluid) {
		this(fluid, FluidValue.EMPTY);
	}

	public FluidStack() {
		this(FluidVariant.blank());
	}

	public FluidStack(Fluid fluid, FluidValue amount) {
		this(FluidVariant.of(fluid), amount);
	}

	public FluidStack(Fluid fluid) {
		this(fluid, FluidValue.EMPTY);
	}

	public FluidStack(NbtCompound tag) {
		this();
		fromTag(tag);
	}

	private FluidVariant getRawFluid() {
		return fluid;
	}

	public FluidVariant getFluid() {
		if (isEmpty()) return FluidVariant.blank();
		else return fluid;
	}

	public FluidValue getAmount() {
		return amount;
	}

	public FluidStack setFluid(FluidVariant fluid) {
		this.fluid = fluid;
		return this;
	}

	public FluidStack setAmount(FluidValue value) {
		this.amount = value;
		return this;
	}

	public FluidStack subtractAmount(FluidValue amount) {
		this.amount = this.amount.subtract(amount);
		return this;
	}

	public FluidStack addAmount(FluidValue amount) {
		this.amount = this.amount.add(amount);
		return this;
	}

	public boolean isEmpty() {
		return this.getRawFluid().isBlank() || this.getAmount().isEmpty();
	}

	public FluidStack copy() {
		return new FluidStack().setFluid(fluid).setAmount(amount);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof FluidStack && fluid == ((FluidStack) obj).getFluid() && amount.equals(((FluidStack) obj).getAmount());
	}

	public boolean isFluidEqual(FluidStack instance) {
		return (isEmpty() && instance.isEmpty()) || fluid.equals(instance.getFluid());
	}

	@Override
	public void fromTag(NbtCompound tag) {
		fluid = FluidVariant.of(Registries.FLUID.get(new Identifier(tag.getString(FLUID_KEY))), tag.contains(TAG_KEY) ? tag.getCompound(TAG_KEY) : null);
		amount = FluidValue.fromRaw(tag.getLong(AMOUNT_KEY));
	}

	@Override
	public @NotNull NbtCompound toTag(NbtCompound tag) {
		tag.putString(FLUID_KEY, Registries.FLUID.getId(fluid.getFluid()).toString());
		tag.putLong(AMOUNT_KEY, amount.getRawValue());
		if (this.fluid.hasNbt()) {
			tag.put(TAG_KEY, this.fluid.getNbt());
		}
		return tag;
	}
}
