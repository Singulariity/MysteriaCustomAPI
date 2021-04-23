package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.effects.CustomEffectType;
import com.mysteria.utils.MysteriaUtils;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SicknessListener implements Listener {

	public SicknessListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void onEat(PlayerItemConsumeEvent e) {

		if (e.getPlayer().hasPotionEffect(CustomEffectType.SICKNESS)) {
			e.setCancelled(true);
			MysteriaUtils.sendMessage(e.getPlayer(),
					Component.text("You cannot consume any item when affected by ", NamedColor.CARMINE_PINK)
							.append(Component.text("Sickness", NamedColor.SKIRRET_GREEN))
							.append(Component.text(".", NamedColor.CARMINE_PINK))
			);
		}

	}

}
