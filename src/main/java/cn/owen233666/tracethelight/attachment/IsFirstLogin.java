package cn.owen233666.tracethelight.attachment;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record IsFirstLogin(Boolean isFirstLogin) {
    public static final IsFirstLogin DEFAULT = new IsFirstLogin(false);

    public static final Codec<IsFirstLogin> CODEC = Codec.BOOL.xmap(
            IsFirstLogin::new,
            IsFirstLogin::isFirstLogin
    );

    public static final StreamCodec<ByteBuf, IsFirstLogin> STREAM_CODEC =
            ByteBufCodecs.BOOL.map(
                    IsFirstLogin::new,
                    IsFirstLogin::isFirstLogin
            );

    public IsFirstLogin set(Boolean bool){
        return new IsFirstLogin(bool);
    }

    @Override
    public String toString(){
        return isFirstLogin.toString();
    }
}
