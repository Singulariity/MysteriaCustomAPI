package com.mysteria.customapi.effects.wrappers;

import com.mysteria.customapi.effects.CustomEffectTypeWrapper;
import com.mysteria.utils.MysteriaUtils;
import com.mysteria.utils.NamedColor;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

public class PurgeWrapper extends CustomEffectTypeWrapper {

	public PurgeWrapper() {
		super(46);
	}

	@Override
	public double getDurationModifier() {
		return 1.0;
	}

	@Override
	public @NotNull String getName() {
		return "PURGE";
	}

	@Override
	public boolean isInstant() {
		return true;
	}

	@Override
	public @NotNull
	Color getColor() {
		return MysteriaUtils.toBukkitColor(NamedColor.HINT_OF_ICE_PACK);
	}

	@NotNull
	@Override
	public MobEffectInfo getInfo() {
		return MobEffectInfo.NEUTRAL;
	}
}
