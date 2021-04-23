package com.mysteria.customapi.deathmessage;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.deathmessage.enums.CustomDeathReason;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDeathManager {

	private final HashMap<UUID, CustomDeathReason> list = new HashMap<>();
	private final @Nonnull Component DEATH_PREFIX = Component.text("[☠] ", NamedColor.CARMINE_PINK);

	public PlayerDeathManager() {
		if (CustomAPIPlugin.getDeathManager() != null) {
			throw new IllegalStateException();
		}
	}

	@Nonnull
	public Component getPREFIX() {
		return DEATH_PREFIX;
	}

	@Nonnull
	public Component getPlayerDeathMessage(@Nonnull Player p, @Nonnull CustomDeathReason reason) {
		return reason.getRandomDeathMessage().replaceText(
				TextReplacementConfig.builder()
						.match("<player>")
						.once()
						.replacement(p.getName())
						.build());
	}

	@Nullable
	public CustomDeathReason getDeathReason(@Nonnull Player p) {
		return list.getOrDefault(p.getUniqueId(), null);
	}

	public void putReason(@Nonnull Player p, @Nonnull CustomDeathReason reason) {
		putReason(p, reason, 3);
	}

	public void putReason(@Nonnull Player p, @Nonnull CustomDeathReason reason, int deleteAfter) {
		list.put(p.getUniqueId(), reason);
		new BukkitRunnable() {
			@Override
			public void run() {
				list.remove(p.getUniqueId());
			}
		}.runTaskLater(CustomAPIPlugin.getInstance(), Math.max(deleteAfter, 3));
	}

}
