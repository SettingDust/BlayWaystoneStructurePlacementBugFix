package settingdust.blaywaystonestructureplacementbugfix;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class BlayWaystoneMixinCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(final List<String> targetClassNames, final String mixinClassName) {
        if (mixinClassName.equals("net.fabricmc.fabric.mixin.datagen.server.MainMixin")) return true;
        return mixinClassName.equals("net.blay09.mods.waystones.mixin.SinglePoolElementMixin") ||
               mixinClassName.equals("net.blay09.mods.waystones.mixin.JigsawPlacementPlacerMixin");
    }
}
