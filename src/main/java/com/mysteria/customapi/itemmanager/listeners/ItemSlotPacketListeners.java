package com.mysteria.customapi.itemmanager.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.itemmanager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ItemSlotPacketListeners implements Listener {

	private final List<Player> noUpdate = new ArrayList<>();

	public ItemSlotPacketListeners() {
		Bukkit.getPluginManager().registerEvents(this, CustomAPIPlugin.getInstance());

		CustomAPIPlugin.getProtocolManager().addPacketListener(new PacketAdapter(CustomAPIPlugin.getInstance(),
				ListenerPriority.NORMAL, PacketType.Play.Server.SET_SLOT, PacketType.Play.Server.WINDOW_ITEMS) {
			@Override
			public void onPacketSending(PacketEvent e) {
				PacketContainer packet = e.getPacket();

				if (noUpdate.contains(e.getPlayer()) || e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

				ItemManager itemManager = CustomAPIPlugin.getItemManager();

				if (e.getPacketType() == PacketType.Play.Server.SET_SLOT) {
					ItemStack item = packet.getItemModifier().read(0);
					if (item != null && item.getType() != Material.AIR) {
						itemManager.setItemPacketMeta(item);
					}
				}
				else if (e.getPacketType() == PacketType.Play.Server.WINDOW_ITEMS) {
					List<ItemStack> itemStacks = packet.getItemListModifier().read(0);
					for (ItemStack item : itemStacks) {
						if (item != null && item.getType() != Material.AIR) {
							itemManager.setItemPacketMeta(item);
						}
					}
				}
				e.setPacket(packet);
			}
		});

		CustomAPIPlugin.getProtocolManager().addPacketListener(new PacketAdapter(CustomAPIPlugin.getInstance(),
				ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_EQUIPMENT) {
			@Override
			public void onPacketSending(PacketEvent e) {
				PacketContainer packet = e.getPacket();

				for (Pair<EnumWrappers.ItemSlot, ItemStack> pair : packet.getSlotStackPairLists().read(0)) {
					if (pair.getSecond() != null && pair.getSecond().getType() != Material.AIR) {
						CustomAPIPlugin.getItemManager().setItemPacketMeta(pair.getSecond());
					}
				}
				e.setPacket(packet);
			}
		});

	}

	@EventHandler(ignoreCancelled = true)
	public void onGamemodeChange(PlayerGameModeChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getNewGameMode() == GameMode.CREATIVE) {
			if (!noUpdate.contains(p)) noUpdate.add(p);
			p.updateInventory();
		} else if (p.getGameMode() == GameMode.CREATIVE) {
			noUpdate.remove(p);
			new BukkitRunnable() {
				@Override
				public void run() {
					p.updateInventory();
				}
			}.runTaskLater(CustomAPIPlugin.getInstance(), 1);
		}

	}

}