package dev.prefex.reforested.datagen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mineable {
	public Type type() default Type.PICKAXE;
	
	enum Type {
		SCOOP,
		PICKAXE,
		AXE,
		SHOVEL
	}
}
