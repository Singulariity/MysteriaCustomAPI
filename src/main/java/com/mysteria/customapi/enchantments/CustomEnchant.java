package com.mysteria.customapi.enchantments;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface CustomEnchant {

	@Nonnull
	EnchType getType();

	@Nullable
	TextColor getTextColor();

	@Nullable
	Map<TextDecoration, TextDecoration.State> getTextDecorations();

}
