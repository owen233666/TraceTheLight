package cn.owen233666.tracethelight.creativetab;

import cn.owen233666.tracethelight.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static cn.owen233666.tracethelight.TracetheLight.MODID;

public class ModcreativeTab {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "tracethelight" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "tracethelight:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("trace_the_light_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tracethelight")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.MEDITATION.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.MEDITATION.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

}
