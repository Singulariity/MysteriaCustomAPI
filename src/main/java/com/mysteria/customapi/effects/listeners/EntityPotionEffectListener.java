package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.deathmessage.enums.CustomDeathReason;
import com.mysteria.customapi.effects.CustomEffect;
import com.mysteria.customapi.effects.CustomEffectType;
import com.mysteria.sanity.SanityPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftEnchantingTable;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftInventoryEnchanting;
import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class EntityPotionEffectListener implements Listener {

	public EntityPotionEffectListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	private void onEffect(EntityPotionEffectEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) return;
		LivingEntity entity = (LivingEntity) e.getEntity();

		PotionEffect effect;
		switch (e.getAction()) {
			case REMOVED:
				effect = e.getOldEffect();
				if (effect == null) break;

				if (effect.getType() == CustomEffectType.DOOM) {
					if (e.getCause() == EntityPotionEffectEvent.Cause.EXPIRATION) {
						if (entity instanceof Player) {
							Player victim = (Player) entity;

							CustomAPIPlugin.getDeathManager().putReason(victim, CustomDeathReason.DOOM);
							victim.setHealth(0);
							victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 2);
						}
					}
				}

				break;

			case ADDED:
				effect = e.getNewEffect();
				if (effect == null) break;

				if (effect.getType() instanceof CustomEffect) {
					if (effect.getType().isInstant()) {
						new BukkitRunnable() {
							@Override
							public void run() {
								((LivingEntity) e.getEntity()).removePotionEffect(effect.getType());
							}
						}.runTaskLater(CustomAPIPlugin.getInstance(), 1);
					}
				}

				if (effect.getType() == CustomEffectType.DOOM) {
					if (entity instanceof Monster) {
						if (!(entity instanceof Boss)) {
							entity.setHealth(0);
						}
					}
				}
				else if (effect.getType() == CustomEffectType.FUSS) {
					if (entity instanceof Player) {
						Player victim = (Player) entity;

						new BukkitRunnable() {
							@Override
							public void run() {
								ArrayList<ItemStack> contents = new ArrayList<>();
								PlayerInventory inv = victim.getInventory();
								for (int i = 0; i < 36; i++) {
									contents.add(inv.getItem(i));
									inv.setItem(i, null);
								}
								contents.add(inv.getItem(40));
								inv.setItem(40, null);
								for (int i = 0; i < 36; i++) {
									ItemStack itemStack = contents.get(new Random().nextInt(contents.size()));
									inv.setItem(i, itemStack);
									contents.remove(itemStack);
								}
								ItemStack itemStack = contents.get(new Random().nextInt(contents.size()));
								inv.setItem(40, itemStack);
								contents.remove(itemStack);
							}
						}.runTaskLater(CustomAPIPlugin.getInstance(), 1);

					}
				}
				else if (effect.getType() == CustomEffectType.INSANITY) {
					if (entity instanceof Player) {
						int toRemove = 10 + effect.getAmplifier() * 5;
						SanityPlugin.getSanityManager().sanityOperation((Player) entity, -toRemove);
					}
				}
				else if (effect.getType() == CustomEffectType.LUCIDNESS) {
					if (entity instanceof Player) {
						int toAdd = 10 + effect.getAmplifier() * 5;
						SanityPlugin.getSanityManager().sanityOperation((Player) entity, toAdd);
					}
				}
				else if (effect.getType() == CustomEffectType.PURGE) {
					if (entity instanceof Player) {
						Player victim = (Player) entity;

						for (PotionEffect potionEffect : victim.getActivePotionEffects()) {
							victim.removePotionEffect(potionEffect.getType());
						}
						victim.setFoodLevel(1);
					}
				}
				else if (effect.getType() == CustomEffectType.REWIND) {
					if (entity instanceof Player) {
						Player victim = (Player) entity;

						Location loc = CustomAPIPlugin.getEffectManager().getRewindLocation(victim);
						if (loc == null) return;

						if (victim.getWorld() == loc.getWorld() && victim.getLocation().distance(loc) < 150) {
							victim.teleport(loc);
							victim.playSound(victim.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 0.5F, 1);
						}

					}
				}


				break;
		}


	}

}
