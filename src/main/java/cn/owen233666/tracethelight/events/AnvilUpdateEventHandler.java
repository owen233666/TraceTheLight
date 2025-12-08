package cn.owen233666.tracethelight.events;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.utils.ConvertHexColor;
import cn.owen233666.tracethelight.utils.ConvertMapColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

import static cn.owen233666.tracethelight.utils.ConponentConvert.convert;

@EventBusSubscriber(modid = TracetheLight.MODID)
public class AnvilUpdateEventHandler {
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent e) {
        net.kyori.adventure.text.Component name = null;
        if (e.getName() != null) {
            String nameStr = ConvertHexColor.convertHexColorCodes(ConvertMapColor.convertMapColorCodes(e.getName()));
            name = MiniMessage.miniMessage().deserialize(nameStr);
        }
        Component mcComponent = convert(name, e.getPlayer().registryAccess());
        ItemStack output = e.getLeft().copy();
        output.set(DataComponents.CUSTOM_NAME, mcComponent);
        e.setOutput(output);
        e.setCost(3);
        e.setMaterialCost(1);
    }
}