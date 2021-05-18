package com.mysteria.customapi.enchantments.wrappers;

import com.destroystokyo.paper.MaterialTags;
import com.mysteria.customapi.enchantments.CustomEnchantment;
import com.mysteria.customapi.enchantments.CustomEnchantmentWrapper;
import com.mysteria.customapi.enchantments.EnchType;
import com.mysteria.utils.NamedColor;
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

public class PiercingChanceWrapper extends CustomEnchantmentWrapper {

	public PiercingChanceWrapper() {
		super("piercing_chance");
	}

	@NotNull
	@Override
	public EnchType getType() {
		return EnchType.PERCENT;
	}

	@Nullable
	@Override
	public TextColor getTextColor() {
		return NamedColor.MIDDLE_BLUE;
	}

	@Nullable
	@Override
	public Map<TextDecoration, TextDecoration.State> getTextDecorations() {
		return null;
	}

	@Override
	public @NotNull String getName() {
		return "Piercing Hit Chance";
	}

	@Override
	public int getMaxLevel() {
		return 8;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
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
		return other == CustomEnchantment.CRITICAL_CHANCE;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return getItemTarget().includes(item) ||
				MaterialTags.AXES.isTagged(item) ||
				EnchantmentTarget.TRIDENT.includes(item) ||
				EnchantmentTarget.ARMOR.includes(item) ||
				MaterialTags.BOWS.isTagged(item);
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.piercing_chance");
	}
}
