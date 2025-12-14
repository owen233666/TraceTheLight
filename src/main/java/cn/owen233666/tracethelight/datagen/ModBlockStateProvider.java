package cn.owen233666.tracethelight.datagen;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.block.ConnectableWindowBlock;
import cn.owen233666.tracethelight.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TracetheLight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

//    private void window(){
//        getVariantBuilder(ModBlocks.OAK_WINDOW.get()).forAllStates(blockState -> {
//            if(blockState.getValue(ConnectableWindowBlock.FACING).equals(Direction.NORTH)){
//                if()
//            } else if (blockState.getValue(ConnectableWindowBlock.FACING).equals(Direction.EAST)) {
//
//            }
//        });
//    }
}
