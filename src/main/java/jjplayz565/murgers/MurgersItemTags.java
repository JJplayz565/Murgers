package jjplayz565.murgers;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MurgersItemTags {
    public static final TagKey<Item> MURGERS = TagKey.of(RegistryKeys.ITEM, Identifier.of("tag.item.murgers.murgers"));
}
