package com.mysteria.customapi.effects.wrappers;

import com.mysteria.customapi.effects.CustomEffectTypeWrapper;
import com.mysteria.utils.MysteriaUtils;
import com.mysteria.utils.NamedColor;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

public class CreativeShockWrapper extends CustomEffectTypeWrapper {

	public CreativeShockWrapper() {
		super(44);
	}

	@Override
	public double getDurationModifier() {
		return 1.0;
	}

	@Override
	public @NotNull String getName() {
		return "CREATIVE_SHOCK";
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public @NotNull
	Color getColor() {
		return MysteriaUtils.toBukkitColor(NamedColor.HELIOTROPE);
	}

	@NotNull
	@Override
	public MobEffectInfo getInfo() {
		return MobEffectInfo.HARMFUL;
	}
}
