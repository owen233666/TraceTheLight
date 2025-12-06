package cn.owen233666.tracethelight.sounds;

import cn.owen233666.tracethelight.TracetheLight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TracetheLight.MODID);

    // 创建唱片机歌曲资源键
    public static final DeferredHolder<SoundEvent, SoundEvent> MEDITATION_MUSIC             =   registerSoundEvent("music.meditation");
    public static final DeferredHolder<SoundEvent, SoundEvent> UTOPIA_MUSIC                 =   registerSoundEvent("music.utopia");
    public static final DeferredHolder<SoundEvent, SoundEvent> REMEMBER_WHO_YOU_ARE_MUSIC   =   registerSoundEvent("music.remember_who_you_are");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCARBOROUGH_FAIR_MUSIC       =   registerSoundEvent("music.scarborough_fair");
    public static final DeferredHolder<SoundEvent, SoundEvent> HAPPY_RESTAURANT             =   registerSoundEvent("music.happy_restaurant");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(TracetheLight.MODID, name)));
    }
}
