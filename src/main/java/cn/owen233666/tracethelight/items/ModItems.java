package cn.owen233666.tracethelight.items;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.block.ModBlocks;
import cn.owen233666.tracethelight.utils.ItemPropertiesHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, TracetheLight.MODID);

    //music discs音乐唱片
    public static final DeferredHolder<Item, Item> MEDITATION           =   ITEMS.register("music_disc_meditation",             () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "meditation")))));
    public static final DeferredHolder<Item, Item> UTOPIA               =   ITEMS.register("music_disc_utopia",                 () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "utopia")))));
    public static final DeferredHolder<Item, Item> REMEMBER_WHO_YOU_ARE =   ITEMS.register("music_disc_remember_who_you_are",   () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "remember_who_you_are")))));
    public static final DeferredHolder<Item, Item> SCARBOROUGH_FAIR     =   ITEMS.register("music_disc_scarborough_fair",       () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "scarborough_fair")))));
    public static final DeferredHolder<Item, Item> HAPPY_RESTAURANT     =   ITEMS.register("music_disc_happy_restaurant",       () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "happy_restaurant")))));

}
