package com.mysteria.customapi.itemmanager.containers;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.items.CustomItem;
import com.mysteria.customapi.itemtags.ItemTag;
import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class ItemTagContainer {

	private static final NamespacedKey key = new NamespacedKey(CustomAPIPlugin.getInstance(), "ItemTags");
	private final ItemStack itemStack;
	private final ArrayList<ItemTag> tags;
	private final ArrayList<ItemTag> customItemTags;

	private ItemTagContainer(@Nonnull ItemStack itemStack) {
		this.itemStack = itemStack;
		byte[] bytes = itemStack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.BYTE_ARRAY);
		if (bytes == null) {
			this.tags = new ArrayList<>();
		} else {
			this.tags = SerializationUtils.deserialize(bytes);
		}
		CustomItem customItem = CustomItem.getCustomItem(itemStack);
		if (customItem != null) {
			this.customItemTags = customItem.getTags();
		} else {
			this.customItemTags = null;
		}
	}

	public static ItemTagContainer get(@Nonnull ItemStack itemStack) {
		return new ItemTagContainer(itemStack);
	}

	public ArrayList<ItemTag> getTags() {
		ArrayList<ItemTag> tags = new ArrayList<>(this.tags);
		if (customItemTags != null) {
			for (ItemTag tag : customItemTags) {
				if (tags.contains(tag)) continue;
				tags.add(tag);
			}
		}
		return tags;
	}

	public void addTag(@Nonnull ItemTag tag) {
		if (!tags.contains(tag)) {
			tags.add(tag);
		}
	}

	public void addTags(@Nonnull ItemTag... tags) {
		for (ItemTag tag : tags) {
			addTag(tag);
		}
	}

	public boolean hasAny() {
		return getTags().size() > 0;
	}

	public boolean hasTag(@Nonnull ItemTag tag) {
		return getTags().contains(tag);
	}

	public void removeTag(@Nonnull ItemTag tag) {
		tags.remove(tag);
	}

	public void removeTags(@Nonnull ItemTag... tags) {
		for (ItemTag tag : tags) {
			removeTag(tag);
		}
	}

	public ItemMeta getUpdatedItemMeta() {
		ItemMeta meta = itemStack.getItemMeta();
		PersistentDataContainer container = meta.getPersistentDataContainer();
		if (tags.size() == 0) {
			container.remove(key);
		} else {
			byte[] bytes = SerializationUtils.serialize(tags);
			container.set(key, PersistentDataType.BYTE_ARRAY, bytes);
		}
		return meta.clone();
	}

	public void update() {
		itemStack.setItemMeta(getUpdatedItemMeta());
	}

}
