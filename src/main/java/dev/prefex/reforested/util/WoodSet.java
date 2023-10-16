package dev.prefex.reforested.util;

import dev.prefex.reforested.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

import static dev.prefex.reforested.Reforested.id;

public class WoodSet {
	public final Block log;
	public final Block strippedLog;
	public final Block wood;
	public final Block strippedWood;
	public final Block planks;
	public final Block leaves;
	public final Block sapling;
	public final Block potted_sapling;
	public final Block door;
	public final Block trapdoor;
	public final Block fence;
	public final Block fenceGate;
	public final Block pressurePlate;
	public final Block button;
	public final Block sign;
	//public final Block wallSign;
	public final Block hangingSign;
	public final Block slab;
	public final Block stairs;

	final BlockSetType blockSetType;
	final WoodType woodType;

	public WoodSet(String name, MapColor topColor, MapColor sideColor) {
		this.blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(id(name));
		this.woodType = WoodTypeBuilder.copyOf(WoodType.OAK).register(id(name), blockSetType);

		this.log = ModBlocks.register(name + "_log", Blocks.createLogBlock(topColor, sideColor));
		this.strippedLog = ModBlocks.register(name + "_stripped_log", Blocks.createLogBlock(topColor, sideColor));
		this.wood = ModBlocks.register(name + "_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(sideColor).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
		this.strippedWood = ModBlocks.register(name + "_stripped_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
		this.planks = ModBlocks.register(name + "_planks", new Block(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
		this.leaves = ModBlocks.register(name + "_leaves", Blocks.createLeavesBlock(BlockSoundGroup.GRASS));
		this.sapling = ModBlocks.register(name + "_sapling", new SaplingBlock(new OakSaplingGenerator(), AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)));
		this.potted_sapling = ModBlocks.register("potted_" + name + "_sapling", Blocks.createFlowerPotBlock(this.sapling));
		this.door = ModBlocks.register(name + "_door", new DoorBlock(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(3.0F).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), blockSetType));
		this.trapdoor = ModBlocks.register(name + "_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.create().mapColor(this.planks.getDefaultMapColor()).instrument(Instrument.BASS).strength(3.0F).nonOpaque().sounds(BlockSoundGroup.WOOD).burnable().pistonBehavior(PistonBehavior.DESTROY), blockSetType));
		this.fence = ModBlocks.register(name + "_fence", new FenceBlock(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
		this.fenceGate = ModBlocks.register(name + "_fence_gate", new TrapdoorBlock(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(3.0F).nonOpaque().allowsSpawning(Blocks::never).burnable(), blockSetType));
		this.pressurePlate = ModBlocks.register(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.create().mapColor(topColor).solid().instrument(Instrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY), blockSetType));
		this.button = ModBlocks.register(name + "_button", Blocks.createWoodenButtonBlock(blockSetType));
		this.sign = ModBlocks.register(name + "_sign", new SignBlock(AbstractBlock.Settings.create().mapColor(topColor).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).burnable(), woodType));
		//this.wallSign = ModBlocks.register(name + "_wall_sign", new WallSignBlock(AbstractBlock.Settings.create().mapColor(topColor).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).burnable(), woodType));
		this.hangingSign = ModBlocks.register(name + "_hanging_sign", new HangingSignBlock(AbstractBlock.Settings.create().mapColor(topColor).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).burnable(), woodType));
		this.slab = ModBlocks.register(name + "_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
		this.stairs = ModBlocks.register(name + "_stairs", new StairsBlock(this.planks.getDefaultState(), AbstractBlock.Settings.create().mapColor(topColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
	}
}
