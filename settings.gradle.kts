extra["minecraft"] = "1.20.1"

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/common.gradle.kts")
apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/fabric.gradle.kts")
apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/mixin.gradle.kts")
apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/modmenu.gradle.kts")

dependencyResolutionManagement.versionCatalogs.named("catalog") {
    library("balm", "maven.modrinth", "balm").version("7.3.6+fabric-1.20.1")
    library("waystones", "maven.modrinth", "waystones").version("14.1.3+fabric-1.20")
    library("totw", "maven.modrinth", "totw-modded").version("1.0.5")
    library("commandstructures", "maven.modrinth", "commandstructures").version("4.1.0+1.20.1_fabric")
    library("lithostitched", "maven.modrinth", "lithostitched").version("1.1.6-1.20.1,fabric")
    library("patched", "maven.modrinth", "patched").version("3.2.3+1.20.1-fabric")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "BlayWaystoneStructurePlacementBugFix"

