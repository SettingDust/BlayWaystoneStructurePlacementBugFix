package settingdust.blaywaystonestructureplacementbugfix.datagen;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.worldgen.modifier.AddTemplatePoolElementsModifier;
import dev.worldgen.lithostitched.worldgen.modifier.Modifier;
import dev.worldgen.lithostitched.worldgen.modifier.predicate.TrueModifierPredicate;
import net.enderturret.patchedmod.data.PatchProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;
import settingdust.blaywaystonestructureplacementbugfix.mixin.GuaranteedPoolElementAccessor;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class Entrypoint implements DataGeneratorEntrypoint {
    public static HolderLookup.Provider registries;

    @Override
    public void onInitializeDataGenerator(final FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(
            (FabricDataGenerator.Pack.Factory<VillageLithostitchedJigsawProvider>)
                (output) -> new VillageLithostitchedJigsawProvider(generator));
        pack.addProvider(VillageWaystoneProvider::new);
    }

    public static class VillageLithostitchedJigsawProvider extends PatchProvider {

        public VillageLithostitchedJigsawProvider(
            final DataGenerator generator
        ) {
            super(generator, PackOutput.Target.DATA_PACK, "blaywaystonestructureplacementbugfix");
        }

        @Override
        public void registerPatches() {
            registerAlternativeJigsaw();
        }

        private void registerAlternativeJigsaw() {
            var locations = Set.of(
                new ResourceLocation("village_plains"),
                new ResourceLocation("village_snowy"),
                new ResourceLocation("village_savanna"),
                new ResourceLocation("village_desert"),
                new ResourceLocation("village_taiga")
            );
            var lookup = registries.lookupOrThrow(Registries.STRUCTURE);
            lookup.listElements().filter(it -> {
                var path = it.key().location();
                return locations.contains(path);
            }).forEach(it -> {
                patch(it.key().location().withPrefix("worldgen/structure/"))
                    .compound()
                    .test("/type", "lithostitched:jigsaw", true)
                    .replace("/type", "lithostitched:jigsaw")
                    .end();
            });
        }
    }

    public static class VillageWaystoneProvider extends FabricCodecDataProvider<Modifier> {

        protected VillageWaystoneProvider(
            final FabricDataOutput dataOutput
        ) {
            super(
                dataOutput,
                PackOutput.Target.DATA_PACK,
                "lithostitched/worldgen_modifier",
                Modifier.CODEC
            );
        }

        @Override
        protected void configure(final BiConsumer<ResourceLocation, Modifier> provider) {
            var lookup = registries.lookupOrThrow(Registries.TEMPLATE_POOL);

            var paths = Set.of("plains", "snowy", "savanna", "taiga");

            for (final var path : paths) {
                provider.accept(new ResourceLocation(
                    "blaywaystonestructureplacementbugfix",
                    "waystone/village/" + path
                ), new AddTemplatePoolElementsModifier(
                    TrueModifierPredicate.INSTANCE,
                    new ResourceLocation("village/" + path + "/houses"),
                    List.of(Pair.of(
                        GuaranteedPoolElementAccessor.create(
                            StructurePoolElement.legacy("waystones:village/common/waystone")
                                                .apply(StructureTemplatePool.Projection.RIGID),
                            1,
                            0
                        ),
                        5
                    )),
                    lookup
                ));
            }

            provider.accept(new ResourceLocation(
                "blaywaystonestructureplacementbugfix",
                "waystone/village/desert"
            ), new AddTemplatePoolElementsModifier(
                TrueModifierPredicate.INSTANCE,
                new ResourceLocation("village/desert/houses"),
                List.of(Pair.of(
                    GuaranteedPoolElementAccessor.create(
                        StructurePoolElement.legacy("waystones:village/desert/waystone")
                                            .apply(StructureTemplatePool.Projection.RIGID),
                        1,
                        0
                    ),
                    5
                )),
                lookup
            ));
        }

        @Override
        public @NotNull String getName() {
            return "Village Waystone";
        }
    }
}
