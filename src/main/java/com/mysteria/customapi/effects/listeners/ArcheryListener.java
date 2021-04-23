package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import com.mysteria.utils.MysteriaUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;

public class ArcheryListener implements Listener {

	public ArcheryListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	private void onBowShoot(EntityShootBowEvent e) {

		if (!(e.getEntity() instanceof Player) || e.getBow() == null || e.getBow().getType() != Material.BOW) return;

		PotionEffect effect = e.getEntity().getPotionEffect(CustomEffectType.ARCHERY);

		if (effect != null && MysteriaUtils.chance(20 + 10 * effect.getAmplifier())) {
			e.setConsumeItem(false);
			if (e.getProjectile() instanceof AbstractArrow) {
				((AbstractArrow) e.getProjectile()).setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
			}
		}

	}

}
