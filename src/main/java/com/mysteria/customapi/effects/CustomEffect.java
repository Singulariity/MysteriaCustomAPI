package com.mysteria.customapi.effects;

import net.minecraft.server.v1_16_R3.MobEffectInfo;

import javax.annotation.Nonnull;

public interface CustomEffect {

	@Nonnull
	public MobEffectInfo getInfo();

}
