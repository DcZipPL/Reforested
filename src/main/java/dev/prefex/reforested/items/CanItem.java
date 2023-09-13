package dev.prefex.reforested.items;

import dev.prefex.reforested.util.ItemFluidInfo;
import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.fluid.FluidValue;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CanItem extends Item {
	public static final long VOLUME = FluidValue.BUCKET.getRawValue();

	public CanItem(Settings settings) {
		super(settings);
	}

	@Override
	public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		if (otherStack.getItem() instanceof BucketItem) {
			boolean flag = addFluid(stack, new FluidStack(((ItemFluidInfo)otherStack.getItem()).getFluid(otherStack), FluidValue.BUCKET));

			if (flag)
				cursorStackReference.set(Items.BUCKET.getDefaultStack());
			return flag;
		}
		return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
	}

	@Override
	public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
		return super.onStackClicked(stack, slot, clickType, player);
	}

	private boolean addFluid(ItemStack stack, FluidStack fluid) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		if (nbtCompound.contains("fluid")) { // TODO: do it through FluidStack
			if (nbtCompound.getCompound("fluid").getLong("amount") >= VOLUME)
				return false;
		}
		// TODO: add other fluid check
		nbtCompound.put("fluid", fluid.toTag(new NbtCompound()));
		return true;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		FluidStack fluid = FluidStack.EMPTY;
		fluid.fromTag(stack.getOrCreateNbt().getCompound("fluid"));

		tooltip.add(Text.translatable(Registries.FLUID.getId(fluid.getFluid().getFluid()).toTranslationKey()).append(Text.literal(" " + fluid.getAmount().toString())).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
		super.appendTooltip(stack, world, tooltip, context);
	}
}
