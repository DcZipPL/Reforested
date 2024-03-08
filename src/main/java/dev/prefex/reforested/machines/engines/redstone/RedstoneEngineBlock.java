package dev.prefex.reforested.machines.engines.redstone;

import com.mojang.serialization.MapCodec;
import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.reforested.machines.engines.stirling.StirlingEngineBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class RedstoneEngineBlock extends BlockWithEntity {
	public static final DirectionProperty FACING = Properties.FACING;

	public RedstoneEngineBlock(Settings settings) {
		super(settings.pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).allowsSpawning(Blocks::never).nonOpaque());
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.with(FACING, mirror.apply(state.get(FACING)));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			RedstoneEngineBlockEntity blockEntity = (RedstoneEngineBlockEntity) world.getBlockEntity(pos);
			if (blockEntity != null) {
				player.sendMessage(Text.of("Current heat: " + blockEntity.heat + " H"));
				player.sendMessage(Text.of("Current frequency: " + blockEntity.getFrequency() + " Hz(t)"));
			}
		}

		return ActionResult.PASS;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RedstoneEngineBlockEntity(pos, state);
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, ModMachines.REDSTONE_ENGINE_BLOCK_ENTITY, RedstoneEngineBlockEntity::tick);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return createCodec(RedstoneEngineBlock::new);
	}
}
