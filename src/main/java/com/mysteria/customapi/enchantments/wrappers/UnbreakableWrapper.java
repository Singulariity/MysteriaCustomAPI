package com.mysteria.customapi.enchantments.wrappers;

import com.mysteria.customapi.enchantments.CustomEnchantment;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnbreakableWrapper extends CustomEnchantmentWrapper {

	public UnbreakableWrapper() {
		super("unbreakable");
	}

	@NotNull
	@Override
	public EnchType getType() {
		return EnchType.NO_VALUE;
	}

	@Nullable
	@Override
	public TextColor getTextColor() {
		return NamedColor.STEEL_PINK;
	}

	@NotNull
	@Override
	public Map<TextDecoration, TextDecoration.State> getTextDecorations() {
		return new HashMap<>(Collections.singletonMap(TextDecoration.BOLD, TextDecoration.State.TRUE));
	}

	@Override
	public @NotNull String getName() {
		return "Unbreakable";
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
		return EnchantmentTarget.BREAKABLE;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment other) {
		return other == DURABILITY || other == CustomEnchantment.MELTING_CURSE;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return getItemTarget().includes(item);
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.unbreakable");
	}
}
