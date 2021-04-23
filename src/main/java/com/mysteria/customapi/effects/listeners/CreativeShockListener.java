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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class CreativeShockListener implements Listener {

	public CreativeShockListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	private void onBreak(BlockBreakEvent e) {

		if (e.getPlayer().hasPotionEffect(CustomEffectType.CREATIVE_SHOCK)) {
			e.setCancelled(true);
			MysteriaUtils.sendMessage(e.getPlayer(),
					Component.text("You cannot break blocks when affected by ", NamedColor.CARMINE_PINK)
							.append(Component.text("Creative Shock", NamedColor.STEEL_PINK))
							.append(Component.text(".", NamedColor.CARMINE_PINK))
			);
		}
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	private void onBreak(BlockPlaceEvent e) {

		if (e.getPlayer().hasPotionEffect(CustomEffectType.CREATIVE_SHOCK)) {
			e.setCancelled(true);
			MysteriaUtils.sendMessage(e.getPlayer(),
					Component.text("You cannot place blocks when affected by ", NamedColor.CARMINE_PINK)
							.append(Component.text("Creative Shock", NamedColor.STEEL_PINK))
							.append(Component.text(".", NamedColor.CARMINE_PINK))
			);
		}
	}


}
