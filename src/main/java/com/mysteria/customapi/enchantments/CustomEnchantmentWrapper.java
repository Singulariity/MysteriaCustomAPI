package com.mysteria.customapi.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import javax.annotation.Nonnull;

public abstract class CustomEnchantmentWrapper extends Enchantment implements CustomEnchant {

	public CustomEnchantmentWrapper(@Nonnull String key) {
		super(NamespacedKey.minecraft(key));
	}

}
