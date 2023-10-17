package dev.prefex.reforested.machines.engines.redstone;

import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.yokai.machine.MachineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneEngineBlock extends MachineBlock {
	public RedstoneEngineBlock(Settings settings) {
		super(settings);
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

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ModMachines.REDSTONE_ENGINE_BLOCK_ENTITY, RedstoneEngineBlockEntity::tick);
	}
}
