package com.mysteria.customapi.itemmanager.containers;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import javax.annotation.Nonnull;
import java.util.List;

@SuppressWarnings("unused")
public class EffectContainer {

	private final ItemStack itemStack;
	private final PotionMeta meta;

	private EffectContainer(@Nonnull ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		Validate.isTrue(meta instanceof PotionMeta, "ItemStack must be a potion.");
		this.itemStack = itemStack;
		this.meta = (PotionMeta) meta;
	}

	@Nonnull
	public static EffectContainer get(@Nonnull ItemStack itemStack) {
		return new EffectContainer(itemStack);
	}

	public List<PotionEffect> getEffects() {
		return meta.getCustomEffects();
	}

	public boolean hasAny() {
		return getEffects().size() > 0;
	}

	public void addEffect(@Nonnull PotionEffect effect) {
		meta.addCustomEffect(effect, true);
	}

	public void setMainEffect(@Nonnull PotionType type) {
		setMainEffect(type, false, false);
	}

	public void setMainEffect(@Nonnull PotionType type, boolean extended, boolean upgraded) {
		meta.setBasePotionData(new PotionData(type, extended, upgraded));
	}

	public boolean removeEffect(@Nonnull PotionEffectType type) {
		return meta.removeCustomEffect(type);
	}

	public void clearEffects() {
		meta.clearCustomEffects();
	}

	@Nonnull
	public PotionMeta getUpdatedItemMeta() {
		return meta.clone();
	}

	public void update() {
		itemStack.setItemMeta(meta);
	}

}
