package dev.prefex.yokai.helpers;

import net.minecraft.util.collection.DefaultedList;

import java.util.AbstractList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class DefaultedListCollector<T> implements Collector<T, DefaultedList<T>, DefaultedList<T>> {
	private final Set<Characteristics> CHARS;

	public DefaultedListCollector() {
		this.CHARS = Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}

	public static <T> DefaultedListCollector<T> toList() {
		return new DefaultedListCollector<>();
	}

	public Supplier<DefaultedList<T>> supplier() {
		return DefaultedList::of;
	}

	public BiConsumer<DefaultedList<T>, T> accumulator() {
		return AbstractList::add;
	}

	public BinaryOperator<DefaultedList<T>> combiner() {
		return (left, right) -> {
			left.addAll(right);
			return left;
		};
	}

	public Function<DefaultedList<T>, DefaultedList<T>> finisher() {
		return (i) -> {
			return i;
		};
	}

	public Set<Collector.Characteristics> characteristics() {
		return this.CHARS;
	}
}
