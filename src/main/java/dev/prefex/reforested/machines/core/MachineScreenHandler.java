package dev.prefex.reforested.machines.core;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public abstract class MachineScreenHandler extends ScreenHandler {
    protected MachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }
}
