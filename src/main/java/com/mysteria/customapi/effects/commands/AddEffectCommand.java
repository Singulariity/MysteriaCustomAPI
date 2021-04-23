package com.mysteria.customapi.effects.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.mysteria.customapi.itemmanager.containers.EffectContainer;
import com.mysteria.utils.MysteriaUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AddEffectCommand extends BaseCommand {

	@Default
	@CommandPermission("customapi.addeffect")
	@CommandCompletion("@potiontype @potionduration @range:0-5")
	@Syntax("<type> <duration> <amplifier>")
	@Description("Adds an effect to the held potion item.")
	public void onCommand(Player p, String[] args) {
		if (args.length < 3) {
			return;
		}

		ItemStack heldItem = p.getInventory().getItemInMainHand();
		if (heldItem.getType() == Material.AIR) {
			MysteriaUtils.sendMessageDarkRed(p, "Held item cannot be air.");
			return;
		}

		if (!(heldItem.getItemMeta() instanceof PotionMeta)) {
			MysteriaUtils.sendMessageDarkRed(p, "Held item must be a potion.");
			return;
		}

		PotionEffectType type = PotionEffectType.getByName(args[0]);
		if (type == null) {
			MysteriaUtils.sendMessageDarkRed(p, "Effect not found.");
			return;
		}

		int duration;
		try {
			duration = Integer.parseInt(args[1]);
		} catch (NumberFormatException ignored) {
			MysteriaUtils.sendMessageDarkRed(p, "Type a valid duration.");
			return;
		}

		int amplifier;
		try {
			amplifier = Integer.parseInt(args[2]);
		} catch (NumberFormatException ignored) {
			MysteriaUtils.sendMessageDarkRed(p, "Type a valid amplifier.");
			return;
		}

		EffectContainer container = EffectContainer.get(heldItem);
		PotionEffect effect = new PotionEffect(type, duration, amplifier);
		container.addEffect(effect);
		container.update();

		p.getInventory().setItemInMainHand(heldItem);
	}

}