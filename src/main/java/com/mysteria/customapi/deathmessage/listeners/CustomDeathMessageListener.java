package com.mysteria.customapi.deathmessage.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.deathmessage.PlayerDeathManager;
import com.mysteria.customapi.deathmessage.enums.CustomDeathReason;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CustomDeathMessageListener implements Listener {

	public CustomDeathMessageListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(ignoreCancelled = true)
	private void onPlayerDeath(PlayerDeathEvent e) {

		Component deathMessage = e.deathMessage();

		if (deathMessage == null) return;

		Player p = e.getEntity();
		PlayerDeathManager deathManager = CustomAPIPlugin.getDeathManager();

		CustomDeathReason reason = deathManager.getDeathReason(p);

		if (reason != null) {
			deathMessage = deathManager.getPlayerDeathMessage(p, reason);
		}

		e.deathMessage(Component.text()
				.append(deathManager.getPREFIX())
				.append(deathMessage.color(NamedColor.HINT_OF_PENSIVE))
				.build());

	}

}
