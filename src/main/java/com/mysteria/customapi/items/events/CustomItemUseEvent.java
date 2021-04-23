package com.mysteria.customapi.items.events;

import com.mysteria.customapi.items.CustomItem;
import com.mysteria.customapi.items.CustomItemUseReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class CustomItemUseEvent extends Event implements Cancellable {

	private final Player player;
	private final CustomItem customItem;
	private final ItemStack itemStack;
	private boolean isCancelled;
	private boolean isUsed;
	private CustomItemUseReason reason;

	public CustomItemUseEvent(@Nonnull Player player, @Nonnull CustomItem customItem, @Nonnull ItemStack itemStack, @Nonnull CustomItemUseReason reason) {
		this.player = player;
		this.customItem = customItem;
		this.itemStack = itemStack;
		this.isCancelled = false;
		this.isUsed = false;
		this.reason = reason;
	}

	public CustomItemUseEvent(@Nonnull Player player, @Nonnull CustomItem customItem, @Nonnull ItemStack itemStack) {
		this(player, customItem, itemStack, CustomItemUseReason.UNSPECIFIED);
	}

	@Override
	public boolean isCancelled() {
		return this.isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	@Override
	public @Nonnull
	HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Nonnull
	public Player getPlayer() {
		return player;
	}

	@Nonnull
	public CustomItem getCustomItem() {
		return customItem;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * Returns the item in hand represented by this event
	 *
	 * @return ItemStack the item used
	 */
	@Nonnull
	public ItemStack getItem() {
		return itemStack;
	}

	@Nonnull
	public CustomItemUseReason getReason() {
		return reason;
	}

	public void setReason(CustomItemUseReason reason) {
		this.reason = reason;
	}

}
