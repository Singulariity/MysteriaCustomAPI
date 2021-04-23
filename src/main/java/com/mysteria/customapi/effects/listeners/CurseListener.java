package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;

public class CurseListener implements Listener {

	public CurseListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(ignoreCancelled = true)
	private void onHealthRegen(EntityRegainHealthEvent e) {
		if (!(e.getEntity() instanceof Player)) return;

		Player p = (Player) e.getEntity();
		PotionEffect effect = p.getPotionEffect(CustomEffectType.CURSE);

		if (effect != null) {

			if (effect.getAmplifier() < 4) {
				double num = 2 + effect.getAmplifier();
				e.setAmount(e.getAmount() / num);
			} else {
				e.setAmount(0);
			}

		}
	}

}
