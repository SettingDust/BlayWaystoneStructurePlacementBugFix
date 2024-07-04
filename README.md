Blay Waystone will block the structure placement in some condition
[https://github.com/celsiusqc/TowersOfTheWildModded/issues/17#issuecomment-2191305831](https://github.com/celsiusqc/TowersOfTheWildModded/issues/17#issuecomment-2191305831)

This mod powered by Lithostitched and Patched. 
Which means the waystone injection is [data-driven](https://github.com/SettingDust/BlayWaystoneStructurePlacementBugFix/blob/main/src/main/generated/data/blaywaystonestructureplacementbugfix/lithostitched/worldgen_modifier/waystone/village/plains.json). You can modify the weight, [count](https://github.com/Apollounknowndev/lithostitched/wiki/Structure-Utilities#guaranteed) by datapack.
In addition, you can decide which template pool to inject into instead of all the ["/houses"(hard-coded)](https://github.com/TwelveIterationMods/Waystones/blob/1.20.1/shared/src/main/java/net/blay09/mods/waystones/mixin/JigsawPlacementPlacerMixin.java#L37). Only the vanilla villages are confirmed to use this suffix, other [villages added in the pack](https://github.com/ChoiceTheorem/ChoiceTheorem-s-overhauled-village/blob/master/common/src/main/resources/data/ctov/worldgen/template_pool/village/beach/house.json) are free to choose the name they want to use.

Report issue to me instead of the original project if you installed this mod and facing the structure generation problem.
