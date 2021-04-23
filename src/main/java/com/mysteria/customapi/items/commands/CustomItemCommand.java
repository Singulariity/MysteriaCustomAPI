package com.mysteria.customapi.items.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.mysteria.customapi.items.CustomItem;
import com.mysteria.utils.MysteriaUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomItemCommand extends BaseCommand {

	@Default
	@CommandPermission("customapi.givecustomitem")
	@CommandCompletion("@customitem @range:1-64")
	@Syntax("<name> [amount]")
	@Description("Give a custom item.")
	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			return;
		}

		for (CustomItem customItem : CustomItem.values()) {
			if (customItem.toString().toLowerCase().equals(args[0])) {
				p.getInventory().addItem(customItem.getItemStack());
				return;
			}
		}

		CustomItem found;
		try	{
			int i = Integer.parseInt(args[0]);
			found = CustomItem.getByID(i);
		} catch (NumberFormatException ignored) {
			found = null;
		}

		if (found == null) {
			MysteriaUtils.sendMessageDarkRed(p, "Custom item not found.");
			return;
		}

		int amount;
		if (args.length >= 2) {
			try {
				amount = Math.max(Integer.parseInt(args[1]), 1);
			} catch (NumberFormatException ignored) {
				MysteriaUtils.sendMessageDarkRed(p, "Type a valid amount.");
				return;
			}
		}  else {
			amount = 1;
		}

		ItemStack itemStack = found.getItemStack(amount);
		p.getInventory().addItem(itemStack);
	}


}
