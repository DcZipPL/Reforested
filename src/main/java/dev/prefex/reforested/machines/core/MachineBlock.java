package dev.prefex.reforested.machines.core;

import net.minecraft.block.BlockWithEntity;

public abstract class MachineBlock extends BlockWithEntity {
    protected MachineBlock(Settings settings) {
        super(settings);
    }
}
