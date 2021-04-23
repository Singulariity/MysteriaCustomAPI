package com.mysteria.customapi.effects;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.enums.BuffType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class EffectManager {

	private final HashMap<UUID, ArrayList<Location>> rewindLocs = new HashMap<>();
	private final int rewindTime = 15;
	private static final Map<PotionEffectType, BuffType> buffData = new HashMap<>();

	public EffectManager() {
		if (CustomAPIPlugin.getEffectManager() != null) {
			throw new IllegalStateException();
		}

		buffData.put(PotionEffectType.SPEED, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.SLOW, BuffType.HARMFUL);
		buffData.put(PotionEffectType.FAST_DIGGING, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.SLOW_DIGGING, BuffType.HARMFUL);
		buffData.put(PotionEffectType.INCREASE_DAMAGE, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.HEAL, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.HARM, BuffType.HARMFUL);
		buffData.put(PotionEffectType.JUMP, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.CONFUSION, BuffType.HARMFUL);
		buffData.put(PotionEffectType.REGENERATION, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.DAMAGE_RESISTANCE, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.FIRE_RESISTANCE, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.WATER_BREATHING, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.INVISIBILITY, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.BLINDNESS, BuffType.HARMFUL);
		buffData.put(PotionEffectType.NIGHT_VISION, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.HUNGER, BuffType.HARMFUL);
		buffData.put(PotionEffectType.WEAKNESS, BuffType.HARMFUL);
		buffData.put(PotionEffectType.POISON, BuffType.HARMFUL);
		buffData.put(PotionEffectType.WITHER, BuffType.HARMFUL);
		buffData.put(PotionEffectType.HEALTH_BOOST, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.ABSORPTION, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.SATURATION, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.GLOWING, BuffType.NEUTRAL);
		buffData.put(PotionEffectType.LEVITATION, BuffType.HARMFUL);
		buffData.put(PotionEffectType.LUCK, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.UNLUCK, BuffType.HARMFUL);
		buffData.put(PotionEffectType.SLOW_FALLING, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.CONDUIT_POWER, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.DOLPHINS_GRACE, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.BAD_OMEN, BuffType.BENEFICIAL);
		buffData.put(PotionEffectType.HERO_OF_THE_VILLAGE, BuffType.BENEFICIAL);

		buffData.put(CustomEffectType.DOOM, BuffType.HARMFUL);
		buffData.put(CustomEffectType.FUSS, BuffType.HARMFUL);
		buffData.put(CustomEffectType.BUNNY, BuffType.HARMFUL);
		buffData.put(CustomEffectType.INSANITY, BuffType.HARMFUL);
		buffData.put(CustomEffectType.LUCIDNESS, BuffType.BENEFICIAL);
		buffData.put(CustomEffectType.BLEED, BuffType.HARMFUL);
		buffData.put(CustomEffectType.BROKEN_LEG, BuffType.HARMFUL);
		buffData.put(CustomEffectType.FEATHERFALL, BuffType.BENEFICIAL);
		buffData.put(CustomEffectType.CAMOUFLAGE, BuffType.BENEFICIAL);
		buffData.put(CustomEffectType.ARCHERY, BuffType.BENEFICIAL);
		buffData.put(CustomEffectType.SICKNESS, BuffType.HARMFUL);
		buffData.put(CustomEffectType.CREATIVE_SHOCK, BuffType.HARMFUL);
		buffData.put(CustomEffectType.CURSE, BuffType.HARMFUL);
		buffData.put(CustomEffectType.PURGE, BuffType.NEUTRAL);
		buffData.put(CustomEffectType.REWIND, BuffType.NEUTRAL);
		buffData.put(CustomEffectType.FROSTBURN, BuffType.HARMFUL);

		rewindWriter();
	}

	private void rewindWriter() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Set<Player> exist = new HashSet<>();
				for (Iterator<Map.Entry<UUID, ArrayList<Location>>> it = rewindLocs.entrySet().iterator(); it.hasNext();) {
					Map.Entry<UUID, ArrayList<Location>> entry = it.next();
					Player p = Bukkit.getPlayer(entry.getKey());
					if (p == null) {
						it.remove();
						continue;
					}
					exist.add(p);
					entry.getValue().add(0, p.getLocation());
					if (entry.getValue().size() > rewindTime) entry.getValue().remove(rewindTime);
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (exist.contains(p)) continue;

					rewindLocs.put(p.getUniqueId(), new ArrayList<>());
				}
			}
		}.runTaskTimer(CustomAPIPlugin.getInstance(), 0, 20);
	}

	@Nullable
	public Location getRewindLocation(@Nonnull Player p) {
		if (rewindLocs.containsKey(p.getUniqueId()) && rewindLocs.get(p.getUniqueId()).size() == rewindTime) {
			return rewindLocs.get(p.getUniqueId()).get(rewindTime - 1);
		}
		return null;
	}

	@Nonnull
	public BuffType getBuffType(@Nonnull PotionEffectType type) {
		return buffData.getOrDefault(type, BuffType.NEUTRAL);
	}


}
