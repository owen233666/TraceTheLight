package cn.owen233666.tracethelight.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @ModifyConstant(
            method = "validateName(Ljava/lang/String;)Ljava/lang/String;",
            constant = @Constant(intValue = 50)
    )
    private static int validateName(int constant){

        return 256;
    }
}
