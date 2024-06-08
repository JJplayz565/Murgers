package jjplayz565.murgers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Murgers implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("Murgers");

	private static final float satMod = 0.563f;

	public static final FoodComponent basicTierBoth = new FoodComponent.Builder().nutrition(8).saturationModifier(satMod).build();
	public static final FoodComponent goldTierMeef = new FoodComponent.Builder().nutrition(10).saturationModifier(satMod).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ticks(30), 0), 1.0f).alwaysEdible().build();
	public static final FoodComponent goldTierBeatFree = new FoodComponent.Builder().nutrition(10).saturationModifier(satMod).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticks(40), 1), 1.0f).alwaysEdible().build();
	public static final FoodComponent netherTierMeef = new FoodComponent.Builder().nutrition(12).saturationModifier(satMod).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ticks(40), 2),1.0f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, ticks(90), 2), 1.0f).alwaysEdible().build();
	public static final FoodComponent netherTierBeatFree = new FoodComponent.Builder().nutrition(12).saturationModifier(satMod).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticks(90), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, ticks(90), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ticks(20), 2), 1.0f).alwaysEdible().build();
	public static final FoodComponent mitesized = new FoodComponent.Builder().nutrition(4).saturationModifier(satMod).snack().build();
	public static final FoodComponent gubby = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, ticks(5), 3), 0.8f).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ticks(5), 2), 0.2f).alwaysEdible().build();

	public static final Murger MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "meefmurger"), new Murger(new Item.Settings().food(basicTierBoth)));
	public static final Murger BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "beatfreemurger"), new Murger(new Item.Settings().food(basicTierBoth)));
	public static final Murger GOLDEN_MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "golden_meefmurger"), new Murger(new Item.Settings().food(goldTierMeef)));
	public static final Murger GOLDEN_BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "golden_beatfreemurger"), new Murger(new Item.Settings().food(goldTierBeatFree)));
	public static final Murger NETHERITE_MEEF_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "netherite_meefmurger"), new Murger(new Item.Settings().food(netherTierMeef).fireproof()));
	public static final Murger NETHERITE_BEATFREE_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "netherite_beatfreemurger"), new Murger(new Item.Settings().food(netherTierBeatFree).fireproof()));
	public static final Murger MITESIZED_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "mitesizedmurger"), new Murger(new Item.Settings().food(mitesized)));
	public static final Murger GUBBY_MURGER = Registry.register(Registries.ITEM, new Identifier("murgers", "gubbymurger"), new Murger(new Item.Settings().food(gubby)));
	
	public static final Identifier EATEN_MURGERS = Registry.register(Registries.CUSTOM_STAT, "murgers_eaten", new Identifier("murgers", "murgers_eaten"));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		
		Stats.CUSTOM.getOrCreateStat(EATEN_MURGERS, StatFormatter.DEFAULT);
		
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, MITESIZED_MURGER, Items.PUMPKIN_PIE);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, MEEF_MURGER, MITESIZED_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_MEEF_MURGER, MEEF_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, NETHERITE_MEEF_MURGER, GOLDEN_MEEF_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, BEATFREE_MURGER, NETHERITE_MEEF_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_BEATFREE_MURGER, BEATFREE_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, NETHERITE_BEATFREE_MURGER, GOLDEN_BEATFREE_MURGER);
		addToItemGroup(ItemGroups.FOOD_AND_DRINK, GUBBY_MURGER, NETHERITE_BEATFREE_MURGER);
	}

	private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item, Item after){
		ItemGroupEvents.modifyEntriesEvent(group).register(content -> {content.addAfter(after, item);});
		String temp = group.toString();
        int beginIndex = temp.indexOf("/ ");
		int endIndex = temp.indexOf("]");
        String groupString = temp.substring(beginIndex + 1, endIndex);
		LOGGER.info("Added " + item + " to item group " + groupString + " after " + after);
	}

	public static int ticks(int seconds){
		return seconds * 20;
	}
}