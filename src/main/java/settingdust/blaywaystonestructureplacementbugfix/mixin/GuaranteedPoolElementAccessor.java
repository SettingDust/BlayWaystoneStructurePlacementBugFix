package settingdust.blaywaystonestructureplacementbugfix.mixin;

import dev.worldgen.lithostitched.worldgen.poolelement.GuaranteedPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuaranteedPoolElement.class)
public interface GuaranteedPoolElementAccessor {
    @Invoker("<init>")
    static GuaranteedPoolElement create(StructurePoolElement delegate, int count, int minDepth) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("GUARANTEED_TYPE")
    static void setGUARANTEED_TYPE(StructurePoolElementType<GuaranteedPoolElement> guaranteedType) {
        throw new AssertionError();
    }
}
