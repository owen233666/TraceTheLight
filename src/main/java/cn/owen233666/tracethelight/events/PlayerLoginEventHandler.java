package cn.owen233666.tracethelight.events;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.attachment.CustomTitle;
import cn.owen233666.tracethelight.attachment.IsFirstLogin;
import cn.owen233666.tracethelight.attachment.ModAttachmentType;
import cn.owen233666.tracethelight.init.ModAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = TracetheLight.MODID)
public class PlayerLoginEventHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        Player player = event.getEntity();
        AttachmentType<CustomTitle> customTitleAttachmentType = ModAttachmentType.getCustomTitleAttachmentType();
        AttachmentType<IsFirstLogin> isFirstLoginAttachmentType = ModAttachmentType.getIsFirstLoginAttachmentType();
        if(!player.level().isClientSide){
            if(!player.hasData(ModAttachmentType.getCustomTitleAttachmentType())){
                TracetheLight.LOGGER.info("玩家" + player.getName() + "没有自定义称号，正在为玩家" + player.getName() + "赋予称号");
                player.setData(customTitleAttachmentType, CustomTitle.DEFAULT);
                TracetheLight.LOGGER.info("已为玩家" + player.getName() + "赋予默认称号");
            }else {
                String title = player.getData(customTitleAttachmentType).toString();
                TracetheLight.LOGGER.info("玩家" + player.getName() + "的称号(无样式)是: " + title);
            }

            if(!player.hasData(isFirstLoginAttachmentType) || player.getData(isFirstLoginAttachmentType).isFirstLogin() == false){
                ModAudiences.TraceTheLightAudiences.player(player.getUUID()).sendMessage(MiniMessage.miniMessage().deserialize(
                    "[<gradient:#FF77B0:#FF9E54>溯光旅纪</gradient>] 欢迎！" + player.getScoreboardName() +"这是您首次加入此世界！<newline>本整合包由owen233666策划制作，完全免费，<reset><bold>如果你花钱购买了此整合包，请立刻退款！<reset><newline><underlined>此消息只会显示一次"
                ));
                player.setData(isFirstLoginAttachmentType, new IsFirstLogin(true));
            }

        }
    }
}
