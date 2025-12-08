package cn.owen233666.tracethelight.events;

import cn.owen233666.tracethelight.Config;
import cn.owen233666.tracethelight.TracetheLight;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = TracetheLight.MODID)
public class ConfigReloadEventHandler {
    public static String DEFAULT_TITLE;
    @SubscribeEvent
    public static void onConfigReloading(ModConfigEvent.Reloading event){
        DEFAULT_TITLE = Config.DEFAULT_TITLE.get();
    }
}
