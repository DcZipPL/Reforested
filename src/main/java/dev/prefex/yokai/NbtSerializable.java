package dev.prefex.yokai;

import net.minecraft.nbt.NbtCompound;

public interface NbtSerializable {
	void fromTag(NbtCompound tag);
	NbtCompound toTag(NbtCompound tag);
}
