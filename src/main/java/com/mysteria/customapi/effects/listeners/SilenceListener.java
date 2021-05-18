package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SilenceListener implements Listener {

	public SilenceListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onChat(AsyncChatEvent e) {
		if (e.getPlayer().hasPotionEffect(CustomEffectType.SILENCE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onChat(PlayerCommandPreprocessEvent e) {
		if (e.getPlayer().hasPotionEffect(CustomEffectType.SILENCE)) {
			e.setCancelled(true);
		}
	}

}
