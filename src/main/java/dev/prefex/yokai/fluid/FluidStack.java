package dev.prefex.yokai.fluid;

import dev.onyxstudios.cca.api.v3.util.NbtSerializable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FluidStack implements NbtSerializable {
	public static final String FLUID_KEY = "Fluid";
	public static final String AMOUNT_KEY = "Amount";
	public static final String TAG_KEY = "Tag";

	public static final FluidStack EMPTY = new FluidStack(Fluids.EMPTY, FluidValue.EMPTY);

	// TODO: move to FluidVariant
	protected Fluid fluid;
	protected FluidValue amount;
	protected NbtCompound tag;

	public FluidStack(Fluid fluid, FluidValue amount) {
		this.fluid = fluid;
		this.amount = amount;
	}

	public FluidStack(Fluid fluid) {
		this(fluid, FluidValue.EMPTY);
	}

	public FluidStack() {
		this(Fluids.EMPTY);
	}

	public FluidStack(NbtCompound tag) {
		this();
		fromTag(tag);
	}

	public Fluid getFluid() {
		return fluid;
	}

	public FluidValue getAmount() {
		return amount;
	}

	public NbtCompound getTag() {
		return tag;
	}

	public FluidStack setFluid(Fluid fluid) {
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

	public void setTag(NbtCompound tag) {
		this.tag = tag;
	}

	public boolean isEmpty() {
		return isEmptyFluid() || this.getAmount().isEmpty();
	}

	public boolean isEmptyFluid() {
		return this.getFluid() == Fluids.EMPTY;
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

	public FluidVariant getVariant() {
		if (isEmpty()) return FluidVariant.blank();
		else return FluidVariant.of(fluid, tag);
	}

	@Override
	public void fromTag(NbtCompound tag) {
		fluid = Registries.FLUID.get(new Identifier(tag.getString(FLUID_KEY)));
		amount = FluidValue.fromRaw(tag.getLong(AMOUNT_KEY));
		if (tag.contains(TAG_KEY)) {
			this.tag = tag.getCompound(TAG_KEY);
		}
	}

	@Override
	public NbtCompound toTag(NbtCompound tag) {
		tag.putString(FLUID_KEY, Registries.FLUID.getId(fluid).toString());
		tag.putLong(AMOUNT_KEY, amount.getRawValue());
		if (this.tag != null && !this.tag.isEmpty()) {
			tag.put(TAG_KEY, this.tag);
		}
		return tag;
	}
}
