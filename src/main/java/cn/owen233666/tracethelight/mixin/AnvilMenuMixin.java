package cn.owen233666.tracethelight.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @ModifyConstant(method = {"setItemName", "validateName"}, constant = @Constant(intValue = 50))
    private static int modifyMaxNameLength(int original) {
        return 512;
    }
}
