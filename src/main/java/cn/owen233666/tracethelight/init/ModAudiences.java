package cn.owen233666.tracethelight.init;

import net.kyori.adventure.platform.modcommon.MinecraftServerAudiences;
import net.kyori.adventure.text.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

public class ModAudiences {
    public static volatile MinecraftServerAudiences TraceTheLightAudiences;

    public static void register(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(ModAudiences.class);
    }

    public static MinecraftServerAudiences adventure() {
        if (TraceTheLightAudiences == null) {
            throw new IllegalStateException("Adventure未初始化！服务器未运行。");
        }
        return TraceTheLightAudiences;
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event){
        TraceTheLightAudiences = MinecraftServerAudiences.of(event.getServer());
        adventure().console().sendMessage(
                Component.text("[TracetheLight]服务器正在启动...")
        );
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event){
        adventure().console().sendMessage(
                Component.text("[TracetheLight]服务器已启动!")
        );
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event){
        adventure().console().sendMessage(
                Component.text("[TracetheLight]服务器正在关闭...")
        );
    }
    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event){
        adventure().console().sendMessage(
                Component.text("[TracetheLight]服务器已关闭!")
        );
    }
}
