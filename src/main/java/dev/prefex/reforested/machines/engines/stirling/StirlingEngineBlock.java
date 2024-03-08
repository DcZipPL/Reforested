package dev.prefex.reforested.machines.engines.stirling;

import com.mojang.serialization.MapCodec;
import dev.prefex.yokai.machine.MachineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StirlingEngineBlock extends MachineBlock {
	public StirlingEngineBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.literal("Can explode if overheated!").setStyle(Style.EMPTY.withColor(Formatting.RED)));
		super.appendTooltip(stack, world, tooltip, options);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return null;
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return createCodec(StirlingEngineBlock::new);
	}
}
