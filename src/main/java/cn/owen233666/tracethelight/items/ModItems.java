package cn.owen233666.tracethelight.items;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.utils.ItemPropertiesHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, TracetheLight.MODID);

    public static final DeferredHolder<Item, Item> MEDITATION           =   ITEMS.register("music_disc.meditation",             () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "meditation")))));
    public static final DeferredHolder<Item, Item> UTOPIA               =   ITEMS.register("music_disc.utopia",                 () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "utopia")))));
    public static final DeferredHolder<Item, Item> REMEMBER_WHO_YOU_ARE =   ITEMS.register("music_disc.remember_who_you_are",   () -> new Item(ItemPropertiesHelper.material(1).rarity(Rarity.EPIC).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, "remember_who_you_wre")))));



}
