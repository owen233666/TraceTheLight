package cn.owen233666.tracethelight.attachment;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CustomTitle(String defaultString) {
    public static final CustomTitle DEFAULT = new CustomTitle("DEFAULT");

    public static final Codec<CustomTitle> CODEC = Codec.STRING.xmap(
            CustomTitle::new,
            CustomTitle::defaultString
    );

    public static final StreamCodec<ByteBuf, CustomTitle> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(
            CustomTitle::new,
            CustomTitle::defaultString
    );

    public CustomTitle withTitle(String newTitle){
        return new CustomTitle(newTitle);
    }

    @Override
    public String toString() {
        return defaultString;
    }
}
