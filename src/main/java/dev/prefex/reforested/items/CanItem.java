package dev.prefex.reforested.items;

import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.fluid.FluidValue;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
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
		if (otherStack.getItem() == Items.WATER_BUCKET) {
			addFluid(stack, new FluidStack(Fluids.WATER, FluidValue.BUCKET));
			cursorStackReference.set(Items.BUCKET.getDefaultStack());
			return true;
		}
		return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
	}

	@Override
	public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
		return super.onStackClicked(stack, slot, clickType, player);
	}

	private void addFluid(ItemStack stack, FluidStack fluid) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		// TODO: Move this to onClicked
		if (nbtCompound.contains("fluid")) { // TODO: do it through FluidStack
			if (nbtCompound.getCompound("fluid").getLong("amount") >= VOLUME)
				return;
		}
		// TODO: add other fluid check

		nbtCompound.put("fluid", fluid.toTag(new NbtCompound()));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		FluidStack fluid = FluidStack.EMPTY;
		fluid.fromTag(stack.getOrCreateNbt().getCompound("fluid"));

		tooltip.add(Text.literal(fluid.getAmount().toString()));
		super.appendTooltip(stack, world, tooltip, context);
	}
}
