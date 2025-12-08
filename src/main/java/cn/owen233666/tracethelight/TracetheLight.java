package cn.owen233666.tracethelight;

import cn.owen233666.tracethelight.attachment.ModAttachmentType;
import cn.owen233666.tracethelight.creativetab.ModcreativeTab;
import cn.owen233666.tracethelight.events.ConfigReloadEventHandler;
import cn.owen233666.tracethelight.init.ModAudience;
import cn.owen233666.tracethelight.items.ModItems;
import cn.owen233666.tracethelight.sounds.ModSounds;
import com.mojang.logging.LogUtils;
import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TracetheLight.MODID)
public class TracetheLight {
    public static final String MODID = "tracethelight";

    public static final Logger LOGGER = LogUtils.getLogger();



    public TracetheLight(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModcreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
        ModAttachmentType.ATTACHMENT_TYPES.register(modEventBus);
        ModAudience.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        ConfigReloadEventHandler.DEFAULT_TITLE = Config.DEFAULT_TITLE.get();
    }
}
