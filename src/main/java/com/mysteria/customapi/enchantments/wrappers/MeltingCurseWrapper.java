package com.mysteria.customapi.enchantments.wrappers;

import com.mysteria.customapi.enchantments.CustomEnchantment;
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

public class MeltingCurseWrapper extends CustomEnchantmentWrapper {

	public MeltingCurseWrapper() {
		super("melting_curse");
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
		return "Curse of Melting";
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
		return EnchantmentTarget.TOOL;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment other) {
		return other == DURABILITY || other == CustomEnchantment.UNBREAKABLE;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return getItemTarget().includes(item);
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.translatable("enchantment.minecraft.melting_curse");
	}
}
