package cn.owen233666.tracethelight.command;

import cn.owen233666.tracethelight.TracetheLight;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = TracetheLight.MODID)
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(CommandBuilder.buildModCommand());
    }
}
