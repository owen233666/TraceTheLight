package cn.owen233666.tracethelight;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> DEFAULT_TITLE = BUILDER
            .comment("默认称号")
            .define("default_title", "&f玩家");

    static final ModConfigSpec SPEC = BUILDER.build();
}
