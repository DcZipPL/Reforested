package dev.prefex.reforested.mixin;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {

	@Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bee_nest")),
			at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/BeehiveBlock;", ordinal = 0))
	private static BeehiveBlock mixin(AbstractBlock.Settings abs) {
		return new BeehiveBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.YELLOW).strength(0.4F).sounds(BlockSoundGroup.WOOD).requiresTool());
	}

}
