package com.mysteria.customapi.rarity;

import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public enum Rarity {

	EVENT(-10, "Event", NamedColor.MATT_PURPLE),
	SPECIAL(-20, "Special", NamedColor.HARLEY_DAVIDSON_ORANGE),
	PREMIUM(-50, "Premium", NamedColor.DOWNLOAD_PROGRESS),

	COMMON(0, "Common", NamedColor.SOARING_EAGLE),
	UNCOMMON(10, "Uncommon", NamedColor.PROTOSS_PYLON),
	RARE(20, "Rare", NamedColor.JUNE_BUD),
	EPIC(30, "Epic", NamedColor.HELIOTROPE),
	HEAVENLY(40, "Heavenly", NamedColor.BEEKEEPER),
	LEGENDARY(50, "Legendary", NamedColor.QUINCE_JELLY);

	private final int weight;
	private final String name;
	private final TextColor color;

	Rarity(int weight, @Nonnull String name, @Nonnull TextColor color) {
		this.weight = weight;
		this.name = name;
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	@Nonnull
	public TextColor getColor() {
		return color;
	}

	@Nonnull
	public String getName() {
		return name;
	}

	@Nonnull
	public Component getDisplayName() {
		return Component.text()
				.append(Component.text(name.toUpperCase(), color))
				.decoration(TextDecoration.ITALIC, false)
				.decoration(TextDecoration.BOLD, true)
				.build();
	}

}
