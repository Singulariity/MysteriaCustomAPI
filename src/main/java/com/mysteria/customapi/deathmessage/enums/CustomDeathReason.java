package com.mysteria.customapi.deathmessage.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import javax.annotation.Nonnull;
import java.util.Random;

public enum CustomDeathReason {
	VOID_CREATURE(
			Component.text("<player> removed from existence by ")
					.append(Component.text("???", NamedTextColor.BLACK)),
			Component.text("<player> was killed by ")
					.append(Component.text("???", NamedTextColor.BLACK))
	),
	DOOM(Component.text("<player> met their doom.")),
	;

	private final Component[] msg;

	CustomDeathReason(@Nonnull Component... msg) {
		this.msg = msg;
	}

	public Component getRandomDeathMessage() {
		return msg[new Random().nextInt(msg.length)];
	}
}
