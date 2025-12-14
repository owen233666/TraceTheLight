package cn.owen233666.tracethelight.block;

import cn.owen233666.tracethelight.TracetheLight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, TracetheLight.MODID);

    public static final DeferredHolder<Block, ConnectableWindowBlock> OAK_WINDOW = BLOCKS.register("oak_window", () -> new ConnectableWindowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F).noOcclusion()));

}
