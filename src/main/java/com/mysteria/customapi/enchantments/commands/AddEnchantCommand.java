package com.mysteria.customapi.enchantments.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.mysteria.utils.MysteriaUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddEnchantCommand extends BaseCommand {

	@Default
	@CommandPermission("customapi.addenchant")
	@CommandCompletion("@enchantment @range:1-5")
	@Syntax("<enchantment> [level]")
	@Description("Adds an enchant to the held item. Ignores enchantment limit.")
	public void onCommand(Player p, String[] args) {

		if (args.length < 1) {
			return;
		}

		ItemStack heldItem = p.getInventory().getItemInMainHand();
		if (heldItem.getType() == Material.AIR) {
			MysteriaUtils.sendMessageDarkRed(p, "Held item cannot be air.");
			return;
		}

		Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(args[0]));
		if (enchantment == null) {
			MysteriaUtils.sendMessageDarkRed(p, "Enchantment not found.");
			return;
		}

		int level;
		if (args.length >= 2) {
			try {
				level = Math.max(Integer.parseInt(args[1]), 1);
			} catch (NumberFormatException ignored) {
				MysteriaUtils.sendMessageDarkRed(p, "Type a valid level.");
				return;
			}
		} else {
			level = 1;
		}

		heldItem.addUnsafeEnchantment(enchantment, level);
		p.getInventory().setItemInMainHand(heldItem);
	}

}
