package dev.prefex.yokai.machine;

import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

public class WrappedDelegate implements PropertyDelegate {
	public int size;
	public Function<Integer, Integer> getter;
	public Consumer<Pair<Integer, Integer>> setter;

	public static WrappedDelegate create(int size, Function<Integer, Integer> getter, Consumer<Pair<Integer, Integer>> setter) {
		return new WrappedDelegate(size, getter, setter);
	}

	private WrappedDelegate(int size, Function<Integer, Integer> getter, Consumer<Pair<Integer, Integer>> setter) {
		this.getter = getter;
		this.setter = setter;
		this.size = size;
	}

	@Override
	public int get(int index) {
		return getter.apply(index);
	}

	@Override
	public void set(int index, int value) {
		setter.accept(new Pair<>(index, value));
	}

	@Override
	public int size() {
		return this.size;
	}
}
