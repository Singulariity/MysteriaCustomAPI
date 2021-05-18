package com.mysteria.customapi.effects.wrappers;

import com.mysteria.customapi.effects.CustomEffectTypeWrapper;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

public class CalamityWrapper extends CustomEffectTypeWrapper {

	public CalamityWrapper() {
		super(49);
	}

	@NotNull
	@Override
	public MobEffectInfo getInfo() {
		return MobEffectInfo.HARMFUL;
	}

	@Override
	public double getDurationModifier() {
		return 1.0;
	}

	@Override
	public @NotNull String getName() {
		return "Calamity";
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public @NotNull Color getColor() {
		return Color.BLACK;
	}
}
