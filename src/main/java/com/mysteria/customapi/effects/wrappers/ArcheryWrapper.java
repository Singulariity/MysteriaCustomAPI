package com.mysteria.customapi.effects.wrappers;

import com.mysteria.customapi.effects.CustomEffectTypeWrapper;
import com.mysteria.utils.MysteriaUtils;
import com.mysteria.utils.NamedColor;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

public class ArcheryWrapper extends CustomEffectTypeWrapper {

	public ArcheryWrapper() {
		super(42);
	}

	@Override
	public double getDurationModifier() {
		return 1.0;
	}

	@Override
	public @NotNull String getName() {
		return "ARCHERY";
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public @NotNull
	Color getColor() {
		return MysteriaUtils.toBukkitColor(NamedColor.BEEKEEPER);
	}

	@NotNull
	@Override
	public MobEffectInfo getInfo() {
		return MobEffectInfo.BENEFICIAL;
	}
}
