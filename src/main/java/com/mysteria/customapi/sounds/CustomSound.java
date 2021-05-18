package com.mysteria.customapi.sounds;

import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public enum CustomSound {

	GALAXY_STARDUST_ORE_BREAK("galaxy.stardust_ore_break"),
	GALAXY_STARCRAFTING_OPEN("galaxy.starcrafting_open"),
	COMBAT_PARRY("combat.parry"),
	COMBAT_RIPOSTE("combat.riposte"),
	SANITY_LAUGH("sanity.laugh"),
	SANITY_WHISPERS("sanity.whispers"),
	SANITY_HEARTBEAT("sanity.heartbeat"),
	OTHER_SWORD_SLICE("other.sword_slice"),
	OTHER_CHAINS("other.chains"),
	OTHER_BOSS_AWOKEN("other.boss_awoken"),
	ITEM_BLOODSTONE("item.bloodstone"),
	MUSIC_GODSHOME("music.godshome"),
	MUSIC_BOSS2("music.boss2"),
	ENCHANTMENT_EMPRESS_BLESSING("enchantment.empress_blessing"),
	ENCHANTMENT_FROSTBITE("enchantment.frostbite");

	private final String key;

	CustomSound(@Nonnull String name) {
		this.key = "custom." + name;
	}

	public String getKey() {
		return key;
	}









	public static void play(@Nonnull Player target, @Nonnull CustomSound customSound, float volume, float pitch) {
		target.playSound(target.getLocation(), customSound.getKey(), volume, pitch);
	}

	public static void play(@Nonnull Player target, @Nonnull CustomSound customSound, @Nonnull SoundCategory category, float volume, float pitch) {
		target.playSound(target.getLocation(), customSound.getKey(), category, volume, pitch);
	}

	public static void play(@Nonnull Location location, @Nonnull CustomSound customSound, float volume, float pitch) {
		location.getWorld().playSound(location, customSound.getKey(), volume, pitch);
	}

	public static void play(@Nonnull Location location, @Nonnull CustomSound customSound, @Nonnull SoundCategory category, float volume, float pitch) {
		location.getWorld().playSound(location, customSound.getKey(), category, volume, pitch);
	}

	public static void stop(@Nonnull Player target, @Nonnull CustomSound customSound) {
		target.stopSound(customSound.getKey());
	}

	public static void stopAll(@Nonnull Player target) {
		for (CustomSound customSound : values()) {
			stop(target, customSound);
		}
	}

}
