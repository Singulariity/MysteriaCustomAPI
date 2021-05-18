package com.mysteria.customapi.items;

import com.mysteria.customapi.itemtags.ItemTag;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

@SuppressWarnings("unused")
public enum CustomItem {

	EMPTY(9999, null, Material.IRON_NUGGET),
	QUESTION_MARK(9998, null, Material.IRON_NUGGET),

	GABRIELS_TEAR(1000,
			Component.text("Gabriel's sacrifice won't be wasted."),
			Material.IRON_NUGGET,
			ItemTag.GLOW, ItemTag.KEEP_ON_DEATH),

	METEORITE_FRAGMENT(1,
			null,
			Material.IRON_NUGGET),
	DIAMOND_SHARD(2,
			null,
			Material.IRON_NUGGET),
	METEORITE_CORE(3,
			null,
			Material.IRON_NUGGET,
			ItemTag.GLOW),
	STARDUST(4,
			null,
			Material.IRON_NUGGET),
	VOID_SUBSTANCE(5,
			null,
			Material.IRON_NUGGET),
	HEART_OF_VOID(6,
			null,
			CustomItemType.ACTIVE,
			Material.IRON_NUGGET),
	OBSIDIAN_SHARD(7,
			null,
			Material.IRON_NUGGET),
	POWERED_DIAMOND(8,
			null,
			Material.IRON_NUGGET,
			ItemTag.GLOW),
	RADIOACTIVE_COAL(9,
			null,
			Material.COAL),
	FLINT_AND_COAL(10,
			Component.text("A common material for furnaces to lit."),
			Material.COAL),
	TERIAS_CHARM(11,
			null,
			Material.IRON_NUGGET,
			ItemTag.KEEP_ON_DEATH),
	IRON_BAND(12,
			null,
			Material.IRON_NUGGET),
	GOLDEN_BAND(13,
			null,
			Material.IRON_NUGGET),
	EMERALD_BAND(14,
			null,
			Material.IRON_NUGGET),
	DIAMOND_BAND(15,
			null,
			Material.IRON_NUGGET),
	NETHERITE_BAND(16,
			null,
			Material.IRON_NUGGET),
	ARCHANGELS_FORGIVENESS(17,
			Component.text("Am I eligible for a miracle?\nForgive me... Archangel..."),
			CustomItemType.PASSIVE,
			Material.IRON_NUGGET),
	RING_OF_SACRIFICE(18,
			Component.text("Sacrifice..?"),
			CustomItemType.PASSIVE,
			Material.IRON_NUGGET),
	STARMETAL_INGOT(19,
			Component.text("Adds the 5th bonus to the item\nthat has four bonuses."),
			Material.IRON_NUGGET),
	BLOODSTONE(20,
			Component.text("A stone with demonic power."),
			CustomItemType.ACTIVE,
			Material.IRON_NUGGET),
	TRUMPET_OF_THE_APOCALYPSE(21,
			null,
			Material.IRON_NUGGET),
	HATCHET(22,
			null,
			Material.IRON_NUGGET,
			ItemTag.KEEP_ON_DEATH),
	FLAMMABLE_ARROW(23,
			null,
			Material.ARROW),
	HOMING_ARROW(24,
			Component.text("Follows the target until hits."),
			Material.ARROW),
	BAT_WING(25,
			null,
			Material.IRON_NUGGET),
	EMPRESS_EYE(26,
			null,
			Material.IRON_NUGGET),
	TOME(27,
			null,
			Material.IRON_NUGGET),
	BLESSING_SCROLL(28,
			Component.text("Eliminates the risk of the\nitem being cursed."),
			Material.IRON_NUGGET,
			ItemTag.GLOW),
	FORBIDDEN_BOOK(29,
			Component.text("'COME to your worst nightmare.'", NamedColor.HARLEY_DAVIDSON_ORANGE)
					.decorate(TextDecoration.OBFUSCATED),
			CustomItemType.ACTIVE,
			Material.IRON_NUGGET),
	BLAZE_FRAGMENT(30,
			null,
			Material.IRON_NUGGET),
	PRISMARINE_ARROW(31,
			Component.text("Shots extra two arrows."),
			Material.ARROW),
	LAPIS_ARROW(32,
			Component.text("Pierces targets up to four."),
			Material.ARROW),
	BLOOD_BOTTLE(33,
			Component.text("Bottled human blood.\nDisgusting.."),
			Material.IRON_NUGGET),
	WOLF_TOOTH(34,
			null,
			Material.IRON_NUGGET),
	VOODOO_DOLL(35,
			Component.text("'You are a terrible person.'"),
			Material.IRON_NUGGET),
	EMPTY_SCROLL(36,
			null,
			Material.IRON_NUGGET),
	BOOK_OF_REGRESSION(37,
			Component.text("'Get out.'"),
			CustomItemType.ACTIVE,
			Material.IRON_NUGGET),
	LEAF(38,
			null,
			Material.IRON_NUGGET),
	TRACKING_COMPASS(39,
			null,
			Material.COMPASS),
	SOULSTONE(40,
			Component.text("Deletes all bonuses from the item."),
			Material.IRON_NUGGET),
	FAIRY_DUST(41,
			Component.text("Adds a new bonus slot to the item and can be\nused until item has a maximum of 4 bonuses."),
			Material.IRON_NUGGET,
			ItemTag.GLOW),
	ANGEL_WINGS(42,
			null,
			CustomItemType.ACCESSORY,
			Material.IRON_NUGGET),
	SPIRIT_ESSENCE(43,
			null,
			Material.IRON_NUGGET),
	SPIRIT_AMULET(44,
			null,
			CustomItemType.ACCESSORY,
			Material.IRON_NUGGET),
	BOOK_OF_ETERNITY(45,
			Component.text("A part of Akashic records."),
			Material.BOOK),
	DAYDREAM_FEATHER(46,
			null,
			Material.IRON_NUGGET),
	VOID_TENDRIL(47,
			null,
			Material.IRON_NUGGET),
	BOOK_OF_CONSCIOUSNESS(48,
			Component.text("Replaces all bonuses of an item by the\nsame number of randomly selected new bonuses.\nThe item should be enchanted with 30 levels before."),
			Material.IRON_NUGGET,
			ItemTag.GLOW),
	BROKEN_TRIDENT(49,
			null,
			Material.IRON_NUGGET)
	;




	private static final Map<Integer, CustomItem> byId = new HashMap<>();
	private final int modelData;
	private final Component name;
	private final Component lore;
	private final Material material;
	private final CustomItemType customItemType;
	private final ItemTag[] tags;

	CustomItem(@Nonnull Integer id, Component lore, @Nonnull CustomItemType customItemType,
			   @Nonnull Material material, @Nonnull ItemTag... tags) {
		this.modelData = id;
		this.name = Component.translatable("item.mystery." + id).decoration(TextDecoration.ITALIC, false);
		this.lore = lore;
		this.customItemType = customItemType;
		this.material = material;
		this.tags = tags;
	}

	CustomItem(@Nonnull Integer id, Component lore, Material material, @Nonnull ItemTag... tags) {
		this(id, lore, CustomItemType.UNSPECIFIED, material, tags);
	}

	public int getModelData() {
		return this.modelData;
	}

	@Nonnull
	public Component getName() {
		return this.name;
	}

	@Nullable
	public List<Component> getLore() {
		if (lore == null) {
			return null;
		} else {
			String[] plainLore = PlainComponentSerializer.plain().serialize(lore).split("\n");
			List<Component> list = new ArrayList<>();
			for (String line : plainLore)  {
				list.add(Component.text(line)
						.color(lore.color())
						.decorations(lore.decorations())
						.colorIfAbsent(NamedColor.SOARING_EAGLE));
			}
			return list;
		}
	}

	@Nonnull
	public CustomItemType getType() {
		return customItemType;
	}

	@Nonnull
	public Material getMaterial() {
		return this.material;
	}

	public ArrayList<ItemTag> getTags() {
		return new ArrayList<>(Arrays.asList(tags));
	}

	@Nonnull
	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(getMaterial());
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(getModelData());
		meta.displayName(getName());
		item.setItemMeta(meta);

		return item;
	}

	@Nonnull
	public ItemStack getItemStack(int amount) {
		ItemStack itemStack = getItemStack();
		itemStack.setAmount(amount);
		return itemStack;
	}


	static {
		for (CustomItem customItem : CustomItem.values()) {
			if (byId.put(customItem.getModelData(), customItem) != null) {
				throw new IllegalArgumentException("Duplicate ID: " + customItem.getModelData());
			}
		}
	}

	@Nullable
	public static CustomItem getByID(int id) {
		return byId.getOrDefault(id, null);
	}


	public static boolean isCustomItem(@Nullable ItemStack itemStack) {
		return itemStack != null && itemStack.getItemMeta().hasCustomModelData();
	}

	public static boolean checkCustomItem(@Nullable ItemStack itemStack, @Nonnull CustomItem customItem) {
		if (isCustomItem(itemStack)) {
			return itemStack.getItemMeta().getCustomModelData() == customItem.getModelData();
		}
		return false;
	}

	@Nullable
	public static CustomItem getCustomItem(@Nullable ItemStack itemStack) {
		if (isCustomItem(itemStack)) {
			return getByID(itemStack.getItemMeta().getCustomModelData());
		}
		return null;
	}



}

