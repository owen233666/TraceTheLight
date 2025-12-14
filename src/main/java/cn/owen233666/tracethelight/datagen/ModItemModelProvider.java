package cn.owen233666.tracethelight.datagen;

import cn.owen233666.tracethelight.TracetheLight;
import cn.owen233666.tracethelight.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TracetheLight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.HAPPY_RESTAURANT.get());
        basicItem(ModItems.MEDITATION.get());
        basicItem(ModItems.REMEMBER_WHO_YOU_ARE.get());
        basicItem(ModItems.SCARBOROUGH_FAIR.get());
        basicItem(ModItems.UTOPIA.get());
    }
}
