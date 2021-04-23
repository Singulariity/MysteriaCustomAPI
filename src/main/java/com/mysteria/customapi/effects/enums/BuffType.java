package com.mysteria.customapi.effects.enums;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import javax.annotation.Nonnull;

public enum BuffType {

	BENEFICIAL(NamedTextColor.BLUE),
	HARMFUL(NamedTextColor.RED),
	NEUTRAL(NamedTextColor.BLUE);

	private final TextColor color;

	BuffType(@Nonnull TextColor color) {
		this.color = color;
	}

	@Nonnull
	public TextColor getColor() {
		return color;
	}
}
