package com.mysteria.customapi.effects.wrappers;

import com.mysteria.customapi.effects.CustomEffectTypeWrapper;
import com.mysteria.utils.MysteriaUtils;
import com.mysteria.utils.NamedColor;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

public class FussWrapper extends CustomEffectTypeWrapper {

	public FussWrapper() {
		super(34);
	}

	@Override
	public double getDurationModifier() {
		return 1.0;
	}

	@Override
	public @NotNull String getName() {
		return "FUSS";
	}

	@Override
	public boolean isInstant() {
		return true;
	}

	@Override
	public @NotNull
	Color getColor() {
		return MysteriaUtils.toBukkitColor(NamedColor.QUINCE_JELLY);
	}

	@NotNull
	@Override
	public MobEffectInfo getInfo() {
		return MobEffectInfo.HARMFUL;
	}
}
