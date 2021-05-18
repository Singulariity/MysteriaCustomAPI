package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CalamityListener implements Listener {

	public CalamityListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	private void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity victim = (LivingEntity) e.getEntity();

			if (victim.hasPotionEffect(CustomEffectType.CALAMITY)) {
				e.setDamage(e.getDamage() * 2);
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	private void onDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getFinalDamage() != 0) {
			LivingEntity victim = (LivingEntity) e.getEntity();
			if (victim.hasPotionEffect(CustomEffectType.CALAMITY)) {
				LivingEntity attacker = null;
				if (e.getDamager() instanceof LivingEntity) {
					attacker = (LivingEntity) e.getDamager();
				}
				else if (e.getDamager() instanceof Projectile) {
					Projectile projectile = (Projectile) e.getDamager();
					if (projectile.getShooter() instanceof LivingEntity) {
						attacker = (LivingEntity) projectile.getShooter();
					}
				}

				if (attacker != null) {
					victim.removePotionEffect(CustomEffectType.CALAMITY);
				}
			}


		}
	}

}
