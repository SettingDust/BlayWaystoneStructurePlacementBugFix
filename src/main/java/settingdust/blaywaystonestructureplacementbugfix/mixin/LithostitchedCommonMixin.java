package settingdust.blaywaystonestructureplacementbugfix.mixin;

import com.mojang.serialization.Codec;
import dev.worldgen.lithostitched.LithostitchedCommon;
import dev.worldgen.lithostitched.worldgen.poolelement.GuaranteedPoolElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(LithostitchedCommon.class)
public class LithostitchedCommonMixin {
    @Inject(method = "registerCommonPoolElementTypes", at = @At("TAIL"))
    private static void blaywaystonestructureplacementbugfix$correctInstance(
        final BiConsumer<String, Codec<? extends StructurePoolElement>> consumer,
        final CallbackInfo ci
    ) {
        var guaranteed = LithostitchedCommon.createResourceKey(Registries.STRUCTURE_POOL_ELEMENT, "guaranteed");
        GuaranteedPoolElementAccessor.setGUARANTEED_TYPE((StructurePoolElementType<GuaranteedPoolElement>) BuiltInRegistries.STRUCTURE_POOL_ELEMENT.getOrThrow(guaranteed));
    }
}
