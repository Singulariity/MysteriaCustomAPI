package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FeatherFallListener implements Listener {

	public FeatherFallListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onFallDamage(EntityDamageEvent e) {

		if (!(e.getEntity() instanceof LivingEntity)) return;

		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {

			LivingEntity entity = (LivingEntity) e.getEntity();

			if (entity.hasPotionEffect(CustomEffectType.FEATHERFALL)) {
				e.setCancelled(true);
			}

		}
	}

}
