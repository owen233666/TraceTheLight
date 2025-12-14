package cn.owen233666.tracethelight.command;

import cn.owen233666.tracethelight.attachment.CustomTitle;
import cn.owen233666.tracethelight.attachment.ModAttachmentType;
import cn.owen233666.tracethelight.init.ModAudiences;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;

public class CommandBuilder {
    private static final AttachmentType<CustomTitle> CUSTOM_TITLE_ATTACHMENTTYPE = ModAttachmentType.getCustomTitleAttachmentType();
    public static LiteralArgumentBuilder<CommandSourceStack> buildModCommand(){
        return Commands.literal("tracethelight")
                .executes(context -> {
                    ModAudiences.TraceTheLightAudiences.audience(context.getSource()).sendMessage(MiniMessage.miniMessage().deserialize(
                            "用法：<newline>/tracethelight setprefix - 设置称号(支持MiniMessage格式)"
                    ));
                    return  1;})
                .then(Commands.literal("setprefix")
                        .then(
                                Commands.argument("prefixstring", StringArgumentType.greedyString())
                                        .executes(context -> {
                                            if(!(context.getSource().source instanceof Player)){
                                                ModAudiences.TraceTheLightAudiences.console().sendMessage(Component.text("[溯光旅纪]只有玩家才能使用此指令！"));
                                                return 1;
                                            }
                                            ServerPlayer player = context.getSource().getPlayer();
                                            player.setData(CUSTOM_TITLE_ATTACHMENTTYPE, new CustomTitle(StringArgumentType.getString(context, "prefixstring")));
                                            return 1;
                                        })
                        )
                )
                .then(Commands.literal("setdefaultprefix")
                        .executes(
                                context -> {
                                    if(!(context.getSource().source instanceof Player)){
                                        ModAudiences.TraceTheLightAudiences.console().sendMessage(Component.text("[溯光旅纪]只有玩家才能使用此指令！"));
                                        return 1;
                                    }
                                    ServerPlayer player = context.getSource().getPlayer();
                                    player.setData(CUSTOM_TITLE_ATTACHMENTTYPE, CustomTitle.DEFAULT);
                                    return 1;
                                }
                        )
                );
    }
}
