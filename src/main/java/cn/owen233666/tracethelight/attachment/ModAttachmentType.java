package cn.owen233666.tracethelight.attachment;

import cn.owen233666.tracethelight.TracetheLight;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

//@EventBusSubscriber(modid = TracetheLight.MODID)
public class ModAttachmentType {



    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TracetheLight.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<CustomTitle>> CUSTOM_TITLE_ATTACHMENT_TYPE =
            ATTACHMENT_TYPES.register(
                    "custom_title",
                    () -> AttachmentType.builder(() -> CustomTitle.DEFAULT)
                            .serialize(CustomTitle.CODEC) // 使用相同的 CODEC 进行序列化
                            .copyOnDeath() // 可选：玩家死亡时是否复制数据
                            .build()
            );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<IsFirstLogin>> IS_FIRST_LOGIN_ATTACHMENTTYPE =
            ATTACHMENT_TYPES.register(
              "is_first_login",
                    () ->AttachmentType.builder(() -> IsFirstLogin.DEFAULT)
                            .serialize(IsFirstLogin.CODEC)
                            .copyOnDeath()
                            .build()
            );

    public static AttachmentType<CustomTitle> getCustomTitleAttachmentType() {
        return CUSTOM_TITLE_ATTACHMENT_TYPE.get();
    }

    public static AttachmentType<IsFirstLogin> getIsFirstLoginAttachmentType() {
        return IS_FIRST_LOGIN_ATTACHMENTTYPE.get();
    }
}

