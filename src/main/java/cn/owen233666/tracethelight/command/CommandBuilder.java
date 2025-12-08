package cn.owen233666.tracethelight.command;

import cn.owen233666.tracethelight.attachment.CustomTitle;
import cn.owen233666.tracethelight.attachment.ModAttachmentType;
import cn.owen233666.tracethelight.init.ModAudience;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentType;

public class CommandBuilder {
    private static final AttachmentType<CustomTitle> CUSTOM_TITLE_ATTACHMENTTYPE = ModAttachmentType.getCustomTitleAttachmentType();
    public static LiteralArgumentBuilder<CommandSourceStack> buildModCommand(){
        return Commands.literal("tracethelight")
                .executes(context -> {
                    ModAudience.TraceTheLightAudiences.audience(context.getSource()).sendMessage(MiniMessage.miniMessage().deserialize(
                            "用法：<newline>/tracethelight setprefix - 设置称号(支持MiniMessage格式)"
                    ));
                    return  1;})
                .then(Commands.literal("setprefix")
                        .then(
                                Commands.argument("prefixstring", StringArgumentType.greedyString())
                                        .executes(context -> {
                                            ServerPlayer player = context.getSource().getPlayer();
                                            player.setData(CUSTOM_TITLE_ATTACHMENTTYPE, new CustomTitle(StringArgumentType.getString(context, "prefixstring")));
                                            return 1;
                                        })
                        )
                );
    }
}
