package dev.prefex.reforested.machines.engines.stirling;

import com.mojang.serialization.MapCodec;
import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.reforested.machines.engines.EngineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StirlingEngineBlock extends EngineBlock {
	public StirlingEngineBlock(Settings settings) {
		super(settings.pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).allowsSpawning(Blocks::never).nonOpaque());
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.literal("Can explode if overheated!").setStyle(Style.EMPTY.withColor(Formatting.RED)));
		super.appendTooltip(stack, world, tooltip, options);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new StirlingEngineBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, ModMachines.STIRLING_ENGINE_BLOCK_ENTITY, StirlingEngineBlockEntity::tick);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return createCodec(StirlingEngineBlock::new);
	}
}
