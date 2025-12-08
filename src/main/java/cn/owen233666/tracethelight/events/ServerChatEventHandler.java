package cn.owen233666.tracethelight.events;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.attachment.CustomTitle;
import cn.owen233666.tracethelight.attachment.ModAttachmentType;
import cn.owen233666.tracethelight.init.ModAudience;
import cn.owen233666.tracethelight.utils.ConvertHexColor;
import cn.owen233666.tracethelight.utils.ConvertMapColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.ServerChatEvent;

@EventBusSubscriber(modid = TracetheLight.MODID)
public class ServerChatEventHandler {
    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event){

        //取消了原版事件，改用Adventure发消息
        event.setCanceled(true);

        AttachmentType<CustomTitle> attachmentType = ModAttachmentType.getCustomTitleAttachmentType();
        Component Player_Name = Component.text(event.getUsername());
        Component Player_Message = MiniMessage.miniMessage().deserialize(event.getRawText());
        String Player_Title_String = event.getPlayer().getData(attachmentType).toString();
        Component Player_Title_Component;
        if(Player_Title_String.equalsIgnoreCase("DEFAULT")){
            Player_Title_Component = MiniMessage.miniMessage().deserialize("[" +
                    ConvertHexColor.convertHexColorCodes(
                            ConvertMapColor.convertMapColorCodes(ConfigReloadEventHandler.DEFAULT_TITLE)
                    ) +
                    "]");
        }else {
            Player_Title_Component = MiniMessage.miniMessage().deserialize("[" + Player_Title_String + "]");
        }
        Component Message = assembleComponent(Player_Title_Component, Player_Name, Component.text(": "), Player_Message);
        ModAudience.TraceTheLightAudiences.players().sendMessage(Message);
        ModAudience.TraceTheLightAudiences.console().sendMessage(Message);
    }

    public static Component assembleComponent(Component... components){
        Component assembled = Component.empty();
        for(Component component : components){
            assembled = assembled.append(component);
        }
        return assembled;
    }
}
