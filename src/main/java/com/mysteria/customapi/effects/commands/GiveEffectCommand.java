package com.mysteria.customapi.effects.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.mysteria.utils.MysteriaUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@CommandAlias("giveeffect")
public class GiveEffectCommand extends BaseCommand {

	@Default
	@CommandPermission("customapi.giveeffect")
	@CommandCompletion("@players @potiontype @potionduration @range:0-5 @nothing")
	@Syntax("<player> <type> [duration] [amplifier]")
	@Description("Gives an effect to the target player.")
	public void onCommand(CommandSender sender, String[] args) {
		if (args.length < 2 || args.length > 4) {
			return;
		}

		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			MysteriaUtils.sendMessageDarkRed(sender, "Target player not found.");
			return;
		}

		PotionEffectType type = PotionEffectType.getByName(args[1]);
		if (type == null) {
			MysteriaUtils.sendMessageDarkRed(sender, "Effect not found.");
			return;
		}

		int duration;
		if (args.length >= 3) {
			try {
				duration = Math.max(Integer.parseInt(args[2]), 20);
			} catch (NumberFormatException ignored) {
				MysteriaUtils.sendMessageDarkRed(sender, "Type a valid duration.");
				return;
			}
		} else duration = 200;

		int amplifier;
		if (args.length == 4) {
			try {
				amplifier = Math.max(Integer.parseInt(args[3]), 0);
			} catch (NumberFormatException ignored) {
				MysteriaUtils.sendMessageDarkRed(sender, "Type a valid amplifier.");
				return;
			}
		} else amplifier = 0;


		PotionEffect effect = new PotionEffect(type, duration, amplifier);
		target.addPotionEffect(effect);
	}

}
