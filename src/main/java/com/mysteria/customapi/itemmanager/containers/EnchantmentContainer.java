package com.mysteria.customapi.itemmanager.containers;

import com.mysteria.customapi.CustomAPIPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.Map;

@SuppressWarnings("unused")
public class EnchantmentContainer {

	private static final NamespacedKey limitKey = new NamespacedKey(CustomAPIPlugin.getInstance(), "EnchantmentsLimit");
	private static final NamespacedKey ascendedKey = new NamespacedKey(CustomAPIPlugin.getInstance(), "EnchantmentsAscended");
	private final ItemStack itemStack;
	private Integer limit;
	private boolean ascended;

	private EnchantmentContainer(@Nonnull ItemStack itemStack) {
		this.itemStack = itemStack;
		PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();
		this.limit = container.get(limitKey, PersistentDataType.INTEGER);
		this.ascended = container.get(ascendedKey, PersistentDataType.INTEGER) != null;
	}

	@Nonnull
	public static EnchantmentContainer get(@Nonnull ItemStack itemStack) {
		return new EnchantmentContainer(itemStack);
	}

	public int getLimit() {
		if (limit == null) {
			return 1;
		} else {
			return Math.max(limit, 1);
		}
	}

	public boolean isAscended() {
		return ascended;
	}

	public void setAscended(boolean ascended) {
		this.ascended = ascended;
	}

	public void setLimit(int value) {
		limit = value;
	}

	@Nonnull
	public Map<Enchantment, Integer> getEnchs() {
		return itemStack.getEnchantments();
	}

	public void addEnch(@Nonnull Enchantment ench, int level) {
		itemStack.addUnsafeEnchantment(ench, level);
	}

	public boolean hasAny() {
		return getEnchs().size() > 0;
	}

	public boolean hasEnch(@Nonnull Enchantment ench) {
		for (Enchantment enchantment : getEnchs().keySet()) {
			if (enchantment == ench) {
				return true;
			}
		}
		return false;
	}

	public int removeEnch(@Nonnull Enchantment ench) {
		return itemStack.removeEnchantment(ench);
	}

	public void clearEnchs() {
		for (Enchantment enchantment : getEnchs().keySet()) {
			itemStack.removeEnchantment(enchantment);
		}
	}

	public ItemMeta getUpdatedItemMeta() {
		ItemMeta meta = itemStack.getItemMeta();
		PersistentDataContainer container = meta.getPersistentDataContainer();
		if (limit != null && limit > 1) {
			container.set(limitKey, PersistentDataType.INTEGER, limit);
		} else {
			container.remove(limitKey);
		}
		if (isAscended()) {
			container.set(ascendedKey, PersistentDataType.INTEGER, 0);
		} else {
			container.remove(ascendedKey);
		}
		return meta.clone();
	}

	public void update() {
		itemStack.setItemMeta(getUpdatedItemMeta());
	}

}
