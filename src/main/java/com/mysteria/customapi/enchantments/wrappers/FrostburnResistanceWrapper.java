package com.mysteria.customapi.enchantments.wrappers;

import com.mysteria.customapi.enchantments.CustomEnchantment;
import com.mysteria.customapi.enchantments.CustomEnchantmentWrapper;
import com.mysteria.customapi.enchantments.EnchType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class FrostburnResistanceWrapper extends CustomEnchantmentWrapper {

	public FrostburnResistanceWrapper() {
		super("frostburn_resistance");
	}

	@NotNull
	@Override
	public EnchType getType() {
		return EnchType.NO_VALUE;
	}

	@Nullable
	@Override
	public TextColor getTextColor() {
		return null;
	}

	@Nullable
	@Override
	public Map<TextDecoration, TextDecoration.State> getTextDecorations() {
		return null;
	}

	@Override
	public @NotNull String getName() {
		return "Resistance against Frostburn";
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}

	@Override
	public int getStartLevel() {
		return 0;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.FISHING_ROD;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment other) {
		return other == CustomEnchantment.SLOW_RESISTANCE ||
				other == CustomEnchantment.LEVITATION_RESISTANCE ||
				other == CustomEnchantment.POISON_RESISTANCE ||
				other == CustomEnchantment.BLEED_RESISTANCE ||
				other == CustomEnchantment.WITHER_RESISTANCE;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return item.getType() == Material.SHIELD;
	}

	@Override
	public @NotNull
	Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.frostburn_resistance");
	}
}
