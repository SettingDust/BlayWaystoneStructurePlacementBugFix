package settingdust.blaywaystonestructureplacementbugfix.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.blaywaystonestructureplacementbugfix.datagen.Entrypoint;

@Mixin(Main.class)
public class MainMixin {
    @Inject(
            method = "main",
            at =
                    @At(
                            value = "INVOKE",
                            shift = At.Shift.BY,
                            by = 2,
                            target = "Lnet/minecraft/core/LayeredRegistryAccess;compositeAccess()Lnet/minecraft/core/RegistryAccess$Frozen;"),
            cancellable = true)
    private static void callDataGen(
            String[] args, CallbackInfo info, @Local RegistryAccess.Frozen registryAccess) {
        if (FabricDataGenHelper.ENABLED) {
            Entrypoint.registries = registryAccess;
            FabricDataGenHelper.run();
            info.cancel();
        }
    }
}
