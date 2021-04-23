package com.mysteria.customapi.enchantments.wrappers;

import com.mysteria.customapi.enchantments.CustomEnchantmentWrapper;
import com.mysteria.customapi.enchantments.EnchType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AxeDefenceWrapper extends CustomEnchantmentWrapper {

	public AxeDefenceWrapper() {
		super("axe_defence");
	}

	@NotNull
	@Override
	public EnchType getType() {
		return EnchType.PERCENT;
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
		return "Axe Defence";
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
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
		return false;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return getItemTarget().includes(item);
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.axe_defence");
	}
}
