package dev.prefex.reforested.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WipBlock extends Block {
	public WipBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.of("Not implemented yet!").getWithStyle(Style.EMPTY.withColor(Formatting.DARK_RED)).get(0));
		super.appendTooltip(stack, world, tooltip, options);
	}
}
