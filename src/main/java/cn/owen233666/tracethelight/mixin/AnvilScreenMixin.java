package cn.owen233666.tracethelight.mixin;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @ModifyArg(method = "subInit()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/EditBox;setMaxLength(I)V"), index = 0)
    private int modifyAnvilNameLength(int originalLength){
        return 512;
    }
}
