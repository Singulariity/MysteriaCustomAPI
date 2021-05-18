package com.mysteria.customapi;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.collect.ImmutableList;
import com.mysteria.customapi.deathmessage.PlayerDeathManager;
import com.mysteria.customapi.deathmessage.listeners.CustomDeathMessageListener;
import com.mysteria.customapi.effects.commands.AddEffectCommand;
import com.mysteria.customapi.effects.commands.GiveEffectCommand;
import com.mysteria.customapi.enchantments.commands.AddEnchantCommand;
import com.mysteria.customapi.items.commands.CustomItemCommand;
import com.mysteria.customapi.effects.CustomEffect;
import com.mysteria.customapi.effects.CustomEffectType;
import com.mysteria.customapi.effects.EffectManager;
import com.mysteria.customapi.effects.listeners.*;
import com.mysteria.customapi.enchantments.CustomEnchantment;
import com.mysteria.customapi.itemmanager.ItemManager;
import com.mysteria.customapi.itemmanager.listeners.ItemSlotPacketListeners;
import com.mysteria.customapi.items.CustomItem;
import com.mysteria.customapi.items.listeners.PrepareItemCraftListener;
import com.mysteria.utils.MysteriaUtils;
import net.minecraft.server.v1_16_R3.MobEffectInfo;
import net.minecraft.server.v1_16_R3.MobEffectList;
import net.minecraft.server.v1_16_R3.MobEffects;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("unused")
public final class CustomAPIPlugin extends JavaPlugin {

	private static CustomAPIPlugin instance;
	private static ItemManager itemManager;
	private static EffectManager effectManager;
	private static PlayerDeathManager deathManager;
	private static ProtocolManager protocolManager;
	private static PaperCommandManager commandManager;

	public CustomAPIPlugin() {
		if (instance != null) throw new IllegalStateException();
		instance = this;
	}

	@Override
	public void onEnable() {

		if (!Bukkit.getPluginManager().isPluginEnabled("MysteriaUtils")) {
			getLogger().severe("*** MysteriaUtils is not installed or not enabled. ***");
			getLogger().severe("*** This plugin will be disabled. ***");
			this.setEnabled(false);
			return;
		}
		if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
			getLogger().severe("*** ProtocolLib is not installed or not enabled. ***");
			getLogger().severe("*** This plugin will be disabled. ***");
			this.setEnabled(false);
			return;
		}

		registerAllEnchantments();
		registerAllEffects();

		itemManager = new ItemManager();
		effectManager = new EffectManager();
		deathManager = new PlayerDeathManager();
		protocolManager = ProtocolLibrary.getProtocolManager();
		commandManager = new PaperCommandManager(getInstance());

		registerListeners();
		registerCommands();
		effectTicker();
	}

	private void registerCommandCompletions() {
		getCommandManager().getCommandCompletions().registerAsyncCompletion("potiontype", c -> {
			ArrayList<String> list = new ArrayList<>();
			for (PotionEffectType type : PotionEffectType.values()) {
				list.add(type.getName().toLowerCase());
			}
			return list;
		});
		getCommandManager().getCommandCompletions().registerAsyncCompletion("potionduration", c ->
				ImmutableList.of("10", "20", "30", "40", "50", "60"));
		getCommandManager().getCommandCompletions().registerAsyncCompletion("enchantment", c -> {
			ArrayList<String> list = new ArrayList<>();
			for (Enchantment enchantment : Enchantment.values()) {
				list.add(enchantment.getKey().getKey());
			}
			return list;
		});
		getCommandManager().getCommandCompletions().registerAsyncCompletion("customitem", c -> {
			ArrayList<String> list = new ArrayList<>();
			for (CustomItem customItem : CustomItem.values()) {
				list.add(customItem.toString().toLowerCase());
			}
			return list;
		});
	}

	private void registerCommands() {
		registerCommandCompletions();
		getCommandManager().registerCommand(new AddEffectCommand());
		getCommandManager().registerCommand(new GiveEffectCommand());
		getCommandManager().registerCommand(new AddEnchantCommand());
		getCommandManager().registerCommand(new CustomItemCommand());
	}

	private void registerListeners() {
		// Item Manager
		new ItemSlotPacketListeners();
		// Items
		new PrepareItemCraftListener();
		// Effects
		new ArcheryListener();
		new CalamityListener();
		new CamouflageListeners();
		new CreativeShockListener();
		new CurseListener();
		new EntityPotionEffectListener();
		new FeatherFallListener();
		new PotionThrowListener();
		new SicknessListener();
		new SilenceListener();
		// Death Message
		new CustomDeathMessageListener();
	}

	private void registerAllEnchantments() {
		registerEnchantment(CustomEnchantment.CRITICAL_DAMAGE);
		registerEnchantment(CustomEnchantment.CRITICAL_CHANCE);
		registerEnchantment(CustomEnchantment.FROSTBITE);
		registerEnchantment(CustomEnchantment.UNBREAKABLE);
		registerEnchantment(CustomEnchantment.SUFFERING_CURSE);
		registerEnchantment(CustomEnchantment.TERIA_CURSE);
		registerEnchantment(CustomEnchantment.MELTING_CURSE);
		registerEnchantment(CustomEnchantment.ALDOUS_CURSE);
		registerEnchantment(CustomEnchantment.REPAIRING_CURSE);
		registerEnchantment(CustomEnchantment.TOMAHAWK);
		registerEnchantment(CustomEnchantment.SWORD_DEFENCE);
		registerEnchantment(CustomEnchantment.AXE_DEFENCE);
		registerEnchantment(CustomEnchantment.ARROW_DEFENCE);
		registerEnchantment(CustomEnchantment.TRIDENT_DEFENCE);
		registerEnchantment(CustomEnchantment.PIERCING_CHANCE);
		registerEnchantment(CustomEnchantment.SLOW_RESISTANCE);
		registerEnchantment(CustomEnchantment.FROSTBURN_RESISTANCE);
		registerEnchantment(CustomEnchantment.LEVITATION_RESISTANCE);
		registerEnchantment(CustomEnchantment.POISON_RESISTANCE);
		registerEnchantment(CustomEnchantment.BLEED_RESISTANCE);
		registerEnchantment(CustomEnchantment.WITHER_RESISTANCE);
		registerEnchantment(CustomEnchantment.EMPRESS_BLESSING);
		registerEnchantment(CustomEnchantment.EXECUTIONER);
	}

	private void registerEnchantment(@Nonnull Enchantment enchantment) {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerAllEffects() {
		try {
			Field byId = PotionEffectType.class.getDeclaredField("byId");
			byId.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(byId, byId.getModifiers() & ~Modifier.FINAL);

			PotionEffectType[] currentValues = (PotionEffectType[]) byId.get(PotionEffectType[].class);
			List<PotionEffectType> values = new ArrayList<>();

			for (int i = 0; i < currentValues.length; i++) {
				if (currentValues[i] != null || i == 0) {
					values.add(currentValues[i]);
				} else {
					break;
				}
			}

			PotionEffectType[] newValues = new PotionEffectType[values.size() + 18];

			for (int i = 0; i < values.size(); i++) {
				newValues[i] = values.get(i);
			}

			byId.set(null, newValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		registerPotionEffectType(CustomEffectType.DOOM);
		registerPotionEffectType(CustomEffectType.FUSS);
		registerPotionEffectType(CustomEffectType.BUNNY);
		registerPotionEffectType(CustomEffectType.INSANITY);
		registerPotionEffectType(CustomEffectType.LUCIDNESS);
		registerPotionEffectType(CustomEffectType.BLEED);
		registerPotionEffectType(CustomEffectType.BROKEN_LEG);
		registerPotionEffectType(CustomEffectType.FEATHERFALL);
		registerPotionEffectType(CustomEffectType.CAMOUFLAGE);
		registerPotionEffectType(CustomEffectType.ARCHERY);
		registerPotionEffectType(CustomEffectType.SICKNESS);
		registerPotionEffectType(CustomEffectType.CREATIVE_SHOCK);
		registerPotionEffectType(CustomEffectType.CURSE);
		registerPotionEffectType(CustomEffectType.PURGE);
		registerPotionEffectType(CustomEffectType.REWIND);
		registerPotionEffectType(CustomEffectType.FROSTBURN);
		registerPotionEffectType(CustomEffectType.CALAMITY);
		registerPotionEffectType(CustomEffectType.SILENCE);
	}

	@SuppressWarnings("deprecation")
	private void registerPotionEffectType(@Nonnull PotionEffectType type) {
		if (type instanceof CustomEffect) {
			try {
				Constructor<MobEffectList> mobEffectListConstructor = MobEffectList.class.getDeclaredConstructor(MobEffectInfo.class, int.class);
				mobEffectListConstructor.setAccessible(true);
				MobEffectList potionInfo = mobEffectListConstructor.newInstance(((CustomEffect) type).getInfo(), colorToInt(type.getColor()) /*Number is color of potion*/);

				Method addPotionToRegistry = MobEffects.class.getDeclaredMethod("a", int.class, String.class, MobEffectList.class);
				addPotionToRegistry.setAccessible(true);
				addPotionToRegistry.invoke(null, type.getId(), type.getName().toLowerCase(Locale.ENGLISH), potionInfo);

				Field f = PotionEffectType.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
				PotionEffectType.registerPotionEffectType(type);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private int colorToInt(@Nonnull Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		blue = blue & 0x000000FF; //Mask out anything not blue.

		return 0xFF000000 | red | green | blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}

	private void effectTicker() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (World world : Bukkit.getWorlds()) {
					for (LivingEntity entity : world.getLivingEntities()) {
						for (PotionEffect effect : entity.getActivePotionEffects()) {

							if (!(effect.getType() instanceof CustomEffect)) continue;

							PotionEffectType type = effect.getType();

							if (type == CustomEffectType.BUNNY) {

								if (entity instanceof Player) {
									((Player) entity).setSprinting(false);
								}
								entity.setVelocity(entity.getVelocity().setY(0.4));

							}
							else if (type == CustomEffectType.BLEED) {

								entity.damage(0.6 + (effect.getAmplifier() * 0.4));
								world.playEffect(entity.getLocation().add(0, 1.3, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);

							}
							else if (type == CustomEffectType.FROSTBURN) {

								int amplifier = effect.getAmplifier();
								boolean inWater = entity.getLocation().getBlock().getType() == Material.WATER;
								if (inWater && !entity.hasPotionEffect(PotionEffectType.SLOW)) {
									entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
											effect.getDuration(), amplifier));
								}
								if (MysteriaUtils.chance(50 + amplifier * 3)) {
									double damage = 0.5 + (amplifier * 0.3) +
											(entity.isSwimming() ? 0.5 : 0) +
											(inWater ? 0.5 : 0);
									entity.damage(damage);
								}

							}

						}
					}
				}
			}
		}.runTaskTimer(this, 0, 20);
	}




	@Nonnull
	public static CustomAPIPlugin getInstance() {
		if (instance == null) throw new IllegalStateException();
		return instance;
	}

	public static ItemManager getItemManager() {
		return itemManager;
	}

	public static EffectManager getEffectManager() {
		return effectManager;
	}

	public static PlayerDeathManager getDeathManager() {
		return deathManager;
	}

	public static ProtocolManager getProtocolManager() {
		return protocolManager;
	}

	public static PaperCommandManager getCommandManager() {
		return commandManager;
	}
}
