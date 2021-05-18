package com.mysteria.customapi.effects.listeners;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.itemmanager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class PotionThrowListener implements Listener {

	public PotionThrowListener() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	private void onPotionThrow(ProjectileLaunchEvent e) {

		if (e.getEntity() instanceof ThrownPotion) {
			ThrownPotion potion = (ThrownPotion) e.getEntity();
			ItemStack itemStack = potion.getItem();

			ItemManager itemManager = CustomAPIPlugin.getItemManager();
			itemManager.setItemPacketMeta(itemStack);

			potion.setItem(itemStack);
		}
	}

}
