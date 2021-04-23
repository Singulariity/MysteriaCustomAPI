package com.mysteria.customapi.enchantments.wrappers;

import com.destroystokyo.paper.MaterialTags;
import com.mysteria.customapi.enchantments.CustomEnchantmentWrapper;
import com.mysteria.customapi.enchantments.EnchType;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TomahawkWrapper extends CustomEnchantmentWrapper {

	public TomahawkWrapper() {
		super("tomahawk");
	}

	@NotNull
	@Override
	public EnchType getType() {
		return EnchType.NO_VALUE;
	}

	@Nullable
	@Override
	public TextColor getTextColor() {
		return NamedColor.QUINCE_JELLY;
	}

	@Nullable
	@Override
	public Map<TextDecoration, TextDecoration.State> getTextDecorations() {
		return null;
	}

	@Override
	public @NotNull String getName() {
		return "Tomahawk";
	}

	@Override
	public int getMaxLevel() {
		return 1;
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
		return false;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return MaterialTags.AXES.isTagged(item);
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.tomahawk");
	}
}
