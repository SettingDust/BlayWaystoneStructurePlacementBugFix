import groovy.lang.Closure

plugins {
    alias(catalog.plugins.fabric.loom)
    alias(catalog.plugins.git.version)
    alias(catalog.plugins.explosion)
}

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/gradle_issue_15754.gradle.kts")

group = "settingdust.blaywaystonestructureplacementbugfix"

val gitVersion: Closure<String> by extra
version = gitVersion()

val id = "blaywaystonestructureplacementbugfix"

fabricApi {
    configureDataGeneration {
        modId = id
    }
}

loom {
    accessWidenerPath = file("src/main/resources/$id.accesswidener")

    mixin {
        defaultRefmapName = "$id.refmap.json"

        useLegacyMixinAp = false
    }
}

dependencies {
    minecraft(catalog.minecraft.fabric)
    mappings(loom.officialMojangMappings())
    modImplementation(catalog.fabric.loader)
    modImplementation(catalog.fabric.api)

    modImplementation(catalog.waystones)
    modRuntimeOnly(catalog.balm)

    modImplementation(catalog.lithostitched)
    modImplementation(catalog.patched)

    modRuntimeOnly(catalog.totw)
    modRuntimeOnly(catalog.commandstructures)

    catalog.mixinsquared.fabric.also {
        include(it)
        implementation(it)
        annotationProcessor(it)
    }
}

tasks {
    val properties = mapOf(
        "id" to id,
        "version" to rootProject.version,
        "group" to rootProject.group,
        "name" to "Blay Waystone Structure Placement Bug Fix",
        "description" to rootProject.property("mod_description").toString(),
        "author" to rootProject.property("mod_author").toString(),
        "source" to rootProject.property("mod_source").toString(),
//            "fabric_loader_version" to rootProject.catalog.versions.fabric.loader.get(),
//            "quilt_loader_version" to rootProject.catalog.versions.quilt.loader.get(),
//            "forge_version" to rootProject.catalog.versions.forge.get(),
    )

    withType<ProcessResources> {
        inputs.properties(properties)
        filesMatching(listOf("fabric.mod.json", "*.mixins.json")) {
            expand(properties)
        }
    }
}
