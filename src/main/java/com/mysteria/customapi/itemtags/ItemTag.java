package com.mysteria.customapi.itemtags;

import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.format.TextColor;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public enum ItemTag {

	KEEP_ON_DEATH(true),
	GLOW(false),
	BLESSED(true, NamedColor.BEEKEEPER),
	;

	private final boolean display;
	private final TextColor color;

	ItemTag(boolean display, @Nonnull TextColor color) {
		this.display = display;
		this.color = color;
	}

	ItemTag(boolean display) {
		this(display, NamedColor.SOARING_EAGLE);
	}

	public boolean showInLore() {
		return display;
	}

	@Nonnull
	public TextColor getTextColor() {
		return color;
	}
}
