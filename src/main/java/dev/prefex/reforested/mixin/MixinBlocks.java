package dev.prefex.reforested.mixin;

import dev.prefex.reforested.items.ReforestedBeehiveBlock;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class MixinBlocks {

	@Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bee_nest")),
			at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/BeehiveBlock;", ordinal = 0))
	private static BeehiveBlock modifyBeeNestSettings(AbstractBlock.Settings abs) {
		return new BeehiveBlock(abs.strength(0.35F).requiresTool());
	}

	@Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=beehive")),
			at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/BeehiveBlock;", ordinal = 0))
	private static BeehiveBlock redirectBeehiveBlock(AbstractBlock.Settings abs) {
		return new ReforestedBeehiveBlock(abs);
	}
}