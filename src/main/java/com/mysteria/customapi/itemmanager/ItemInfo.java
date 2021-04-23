package com.mysteria.customapi.itemmanager;

import com.mysteria.customapi.itemmanager.containers.EffectContainer;
import com.mysteria.customapi.itemmanager.containers.EnchantmentContainer;
import com.mysteria.customapi.itemmanager.containers.ItemTagContainer;
import com.mysteria.customapi.items.CustomItem;
import com.mysteria.customapi.rarity.ItemRarities;
import com.mysteria.customapi.rarity.Rarity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class ItemInfo {

	private final ItemStack itemStack;

	public ItemInfo(@Nonnull ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public static ItemInfo get(@Nonnull ItemStack itemStack) {
		return new ItemInfo(itemStack);
	}

	@Nonnull
	public Rarity getRarity() {
		try {
			if (getCustomItem() == null) {
				return ItemRarities.valueOf(itemStack.getType().toString()).getRarity();
			} else {
				return ItemRarities.valueOf(getCustomItem().toString()).getRarity();
			}
		} catch (Exception ignored) {
			return Rarity.COMMON;
		}
	}

	public boolean isCustomItem() {
		return getCustomItem() != null;
	}

	@Nullable
	public CustomItem getCustomItem() {
		return CustomItem.getCustomItem(itemStack);
	}

	@Nonnull
	public ItemTagContainer getItemTagContainer() {
		return ItemTagContainer.get(itemStack);
	}

	@Nonnull
	public EffectContainer getEffectContainer() {
		return EffectContainer.get(itemStack);
	}

	@Nonnull
	public EnchantmentContainer getEnchantmentContainer() {
		return EnchantmentContainer.get(itemStack);
	}

}
