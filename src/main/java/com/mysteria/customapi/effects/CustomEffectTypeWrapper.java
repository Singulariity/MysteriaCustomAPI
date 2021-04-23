package com.mysteria.customapi.effects;

import org.bukkit.potion.PotionEffectType;

public abstract class CustomEffectTypeWrapper extends PotionEffectType implements CustomEffect {

	public CustomEffectTypeWrapper(int id) {
		super(id);
	}

}