package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class CamouflageListeners implements Listener {

	public CamouflageListeners() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onTarget(EntityTargetLivingEntityEvent e) {

		if (e.getTarget() == null || !(e.getTarget() instanceof Player)) return;
		if (e.getEntity() instanceof Boss) return;

		if (e.getTarget().hasPotionEffect(CustomEffectType.CAMOUFLAGE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onMeleeAttack(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			if (damager.hasPotionEffect(CustomEffectType.CAMOUFLAGE)) {
				damager.removePotionEffect(CustomEffectType.CAMOUFLAGE);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onBowShoot(EntityShootBowEvent e) {

		if (e.getEntity() instanceof Player) {
			Player shooter = (Player) e.getEntity();
			if (shooter.hasPotionEffect(CustomEffectType.CAMOUFLAGE)) {
				shooter.removePotionEffect(CustomEffectType.CAMOUFLAGE);
			}
		}
	}

}
