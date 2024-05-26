package jjplayz565.murgers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Murgers implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("murgers");

	public static final FoodComponent basicTierBoth = new FoodComponent.Builder().nutrition(8).build();
	public static final FoodComponent goldTierMeef = new FoodComponent.Builder().nutrition(10).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ticks(30), 0), 1.0f).alwaysEdible().build();
	public static final FoodComponent goldTierBeatFree = new FoodComponent.Builder().nutrition(10).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticks(40), 1), 1.0f).alwaysEdible().build();
	public static final FoodComponent netherTierMeef = new FoodComponent.Builder().nutrition(12).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ticks(50), 1),1.0f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, ticks(90), 1), 1.0f).alwaysEdible().build();
	public static final FoodComponent netherTierBeatFree = new FoodComponent.Builder().nutrition(12).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticks(70), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, ticks(40), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ticks(40), 0), 1.0f).alwaysEdible().build();

	public static final Murger MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "meefmurger"), new Murger(new Item.Settings().food(basicTierBoth)));
	public static final Murger BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "beatfreemurger"), new Murger(new Item.Settings().food(basicTierBoth)));
	public static final Murger GOLDEN_MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "golden_meefmurger"), new Murger(new Item.Settings().food(goldTierMeef)));
	public static final Murger GOLDEN_BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "golden_beatfreemurger"), new Murger(new Item.Settings().food(goldTierBeatFree)));
	public static final Murger NETHERITE_MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "netherite_meefmurger"), new Murger(new Item.Settings().food(netherTierMeef).fireproof()));
	public static final Murger NETHERITE_BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "netherite_beatfreemurger"), new Murger(new Item.Settings().food(netherTierBeatFree).fireproof()));

	public static final Identifier EAT_MURGERS = new Identifier("murgers", "murgers_eaten");
	

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Registry.register(Registries.CUSTOM_STAT, "murgers_eaten", EAT_MURGERS);
		Stats.CUSTOM.getOrCreateStat(EAT_MURGERS, StatFormatter.DEFAULT);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, NETHERITE_BEATFREE_MURGER);});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, GOLDEN_BEATFREE_MURGER);});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, BEATFREE_MURGER);});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, NETHERITE_MEEF_MURGER);});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, GOLDEN_MEEF_MURGER);});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {content.addAfter(Items.PUMPKIN_PIE, MEEF_MURGER);});
	}

	public static int ticks(int seconds){
		return seconds * 20;
	}
}