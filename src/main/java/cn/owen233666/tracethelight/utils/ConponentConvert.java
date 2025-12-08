package cn.owen233666.tracethelight.utils;

import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;

public class ConponentConvert {
    public static Component convert(net.kyori.adventure.text.Component component, HolderLookup.Provider registry) {
        try {
            String json = GsonComponentSerializer.gson().serialize(component);
            return Component.Serializer.fromJson(json, registry);
        } catch (Exception e) {
            return Component.translatable("tracethelight.message.err.componentconverterr").withStyle(ChatFormatting.RED);
        }
    }
}
