package dev.prefex.reforested.machines.engines.creative;

import com.mojang.serialization.MapCodec;
import dev.prefex.yokai.machine.MachineBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CreativeEngineBlock extends MachineBlock {
	public CreativeEngineBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		player.sendMessage(Text.literal("Current output: 1 FE"));
		return super.onUse(state, world, pos, player, hand, hit);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CreativeEngineBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return createCodec(CreativeEngineBlock::new);
	}
}
