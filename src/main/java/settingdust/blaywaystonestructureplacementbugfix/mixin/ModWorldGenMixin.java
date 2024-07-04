package settingdust.blaywaystonestructureplacementbugfix.mixin;

import net.blay09.mods.waystones.worldgen.ModWorldGen;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModWorldGen.class)
public class ModWorldGenMixin {
    @Inject(method = "setupDynamicRegistries", at = @At("HEAD"), cancellable = true)
    private static void blaywaystonestructureplacementbugfix$cancelInjection(
        final RegistryAccess registryAccess,
        final CallbackInfo ci
    ) {
        ci.cancel();
    }
}
