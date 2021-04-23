package com.mysteria.customapi.items.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.items.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraftListener implements Listener {

	public PrepareItemCraftListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(ignoreCancelled = true)
	private void onPrepareCraft(PrepareItemCraftEvent e) {

		if (e.getInventory().getResult() == null) return;

		for (ItemStack x : e.getInventory().getMatrix()) {
			if (CustomItem.isCustomItem(x)) {
				e.getInventory().setResult(null);
				break;
			}
		}
	}

}
