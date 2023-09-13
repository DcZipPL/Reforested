package dev.prefex.reforested.mixin;

import dev.prefex.reforested.util.ItemFluidInfo;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BucketItem.class)
public class MixinBucketItem implements ItemFluidInfo {

	@Shadow
	@Final
	private Fluid fluid;

	@Override
	public ItemStack getEmpty() {
		return new ItemStack(Items.BUCKET);
	}

	@Override
	public ItemStack getFull(Fluid fluid) {
		return new ItemStack(fluid.getBucketItem());
	}

	@Override
	public Fluid getFluid(ItemStack itemStack) {
		return fluid;
	}
}