package com.mysteria.customapi.itemmanager;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.enchantments.CustomEnchant;
import com.mysteria.customapi.enchantments.EnchType;
import com.mysteria.customapi.itemmanager.containers.EffectContainer;
import com.mysteria.customapi.itemmanager.containers.EnchantmentContainer;
import com.mysteria.customapi.itemmanager.containers.ItemTagContainer;
import com.mysteria.customapi.items.CustomItem;
import com.mysteria.customapi.itemtags.ItemTag;
import com.mysteria.utils.NamedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ItemManager {

	public ItemManager() {
		if (CustomAPIPlugin.getItemManager() != null) {
			throw new IllegalStateException();
		}
	}

	public void setItemPacketMeta(@Nonnull ItemStack itemStack) {

		ItemMeta meta = itemStack.getItemMeta();

		if (meta.hasItemFlag(ItemFlag.HIDE_DESTROYS)) return;

		boolean glow = false;
		ItemInfo itemInfo = ItemInfo.get(itemStack);
		List<Component> itemLore = new ArrayList<>();

		// CUSTOM POTION EFFECTS PART
		if (meta instanceof PotionMeta) {
			EffectContainer effectContainer = itemInfo.getEffectContainer();
			if (effectContainer.hasAny()) {
				List<PotionEffect> effects = effectContainer.getEffects();

				PotionMeta potionMeta = ((PotionMeta) meta);
				PotionEffect first = effects.get(0);

				if (potionMeta.getBasePotionData().getType().getEffectType() == null) {
					Component var = Component.translatable(
							"potion.type." + itemStack.getType().toString().toLowerCase());
					Component name = Component.translatable(
							"item.mystery.potion.effect." + first.getType().getName().toLowerCase(), var)
							.decoration(TextDecoration.ITALIC, false);

					glow = true;
					potionMeta.setColor(first.getType().getColor());
					potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					potionMeta.displayName(name);
				}

				for (PotionEffect effect : effects) {

					String timeText;
					if (effect.getType().isInstant()) {
						timeText = null;
					} else {
						timeText = getDurationText(effect.getDuration());
					}

					Component line = Component.translatable("potion.withAmplifier",
							Component.translatable("effect.minecraft." + effect.getType().getName().toLowerCase()),
							Component.translatable("potion.potency." + effect.getAmplifier()));
					if (timeText != null) {
						line = Component.translatable("potion.withDuration", line, Component.text(timeText));
					}

					line = line.color(CustomAPIPlugin.getEffectManager().getBuffType(effect.getType()).getColor())
							.decoration(TextDecoration.ITALIC, false);

					itemLore.add(line);
				}
			}
		}

		// ITEM DESCRIPTION PART
		List<Component> itemDesc;
		CustomItem customItem = itemInfo.getCustomItem();
		if (meta.lore() != null) {
			itemDesc = meta.lore();
		}
		else if (customItem != null) {
			itemDesc = customItem.getLore();
		} else {
			itemDesc = null;
		}
		if (itemDesc != null) {
			itemLore.add(Component.space());
			for (Component line : itemDesc) {
				itemLore.add(Component.space().append(line).decoration(TextDecoration.ITALIC, false));
			}
		}

		// ITEM ENCHANTMENTS PART
		EnchantmentContainer enchContainer = itemInfo.getEnchantmentContainer();
		if (enchContainer.hasAny() || enchContainer.getLimit() > 1 || enchContainer.isAscended()) {

			boolean isEmpty = !enchContainer.hasAny();

			Component title = Component.text()
					.append(Component.space())
					.append(Component.translatable("mystery.lore.enchantments.title",
							Component.text(enchContainer.getEnchs().size()),
							Component.text(enchContainer.getLimit())))
					.color(enchContainer.isAscended() ? NamedColor.BEEKEEPER : NamedColor.SILVER)
					.decoration(TextDecoration.ITALIC, false)
					.build();
			itemLore.add(Component.space());
			itemLore.add(title);

			if (isEmpty) {
				Component none = Component.text()
						.append(Component.text("  • ", NamedColor.SILVER))
						.append(Component.translatable("mystery.lore.enchantments.empty", NamedColor.SOARING_EAGLE))
						.decoration(TextDecoration.ITALIC, false)
						.build();
				itemLore.add(none);
			} else {
				for (Map.Entry<Enchantment, Integer> entry : enchContainer.getEnchs().entrySet()) {
					Component enchName = Component.translatable("enchantment.minecraft." + entry.getKey().getKey().getKey());
					int enchValue = entry.getValue();

					Component valuePart;
					Component enchLine;
					TextColor textColor = NamedColor.PROTOSS_PYLON;
					Map<TextDecoration, TextDecoration.State> decorations = null;
					if (entry.getKey() instanceof CustomEnchant) {
						CustomEnchant customEnchant = (CustomEnchant) entry.getKey();
						textColor = customEnchant.getTextColor();
						EnchType enchType = customEnchant.getType();
						decorations = customEnchant.getTextDecorations();

						switch (enchType) {
							case LEVELED:
								if (textColor == null) {
									textColor = NamedColor.PROTOSS_PYLON;
								}
								valuePart = Component.translatable("enchantment.level." + enchValue, textColor);

								break;
							case PERCENT:
								if (textColor == null) {
									textColor = NamedColor.DOWNLOAD_PROGRESS;
								}
								valuePart = Component.text(enchValue + "%", textColor);

								break;
							case NO_VALUE:
							default:
								if (textColor == null) {
									textColor = NamedColor.TURBO;
								}
								valuePart = null;
								break;
						}

					} else {
						valuePart = Component.translatable("enchantment.level." + enchValue, textColor);
					}

					if (entry.getKey().isCursed()) {
						valuePart = null;
						textColor = NamedColor.CARMINE_PINK;
					}

					enchLine = Component.text()
							.append(enchName
									.color(textColor))
							.build();
					if (decorations != null) {
						enchLine = enchLine.decorations(decorations);
					}

					enchLine = Component.text()
							.append(Component.text("  • ", NamedColor.SILVER))
							.append(enchLine)
							.build();

					if (valuePart != null) {
						enchLine = enchLine
								.append(Component.text(" [", NamedColor.WIZARD_GREY))
								.append(valuePart.decorate(TextDecoration.BOLD))
								.append(Component.text("]", NamedColor.WIZARD_GREY));
					}
					itemLore.add(enchLine.decoration(TextDecoration.ITALIC, false));
				}
			}
		}

		// ITEM TAGS PART
		ItemTagContainer itemTagContainer = itemInfo.getItemTagContainer();
		if (itemTagContainer.hasAny()) {
			int added = 0;

			ArrayList<ItemTag> tags = itemTagContainer.getTags();
			glow = tags.contains(ItemTag.GLOW);

			for (ItemTag tag : tags) {
				if (tag.showInLore()) {
					Component desc = Component.text("  • ", NamedColor.SILVER)
							.append(Component.translatable("mystery.itemtag." + tag.toString().toLowerCase(), tag.getTextColor()))
							.decoration(TextDecoration.ITALIC, false);
					itemLore.add(desc);
					added++;
				}
			}
			if (added != 0) {
				Component title = Component.text()
						.append(Component.space())
						.append(Component.translatable("mystery.lore.itemtags.title"))
						.color(NamedColor.SILVER)
						.decoration(TextDecoration.ITALIC, false)
						.build();
				itemLore.add(itemLore.size() - added, Component.space());
				itemLore.add(itemLore.size() - added, title);
			}
		}

		// ITEM RARITY PART
		itemLore.add(Component.space());
		itemLore.add(itemInfo.getRarity().getDisplayName()
				.decoration(TextDecoration.ITALIC, false));



		// ITEM GLOW PART
		if (glow) {
			meta.addEnchant(Enchantment.LURE, 5, true);
		}

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.lore(itemLore);
		itemStack.setItemMeta(meta);
	}

	private String getDurationText(int ticks) {
		int seconds = (ticks / 20) % 60;
		int minutes = ticks / (60 * 20);
		String secondsText = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
		return minutes + ":" + secondsText;
	}

}
