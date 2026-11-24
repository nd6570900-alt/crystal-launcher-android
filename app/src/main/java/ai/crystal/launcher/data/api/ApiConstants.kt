package ai.crystal.launcher.data.api

/**
 * Exact endpoints used by Crystal Launcher desktop v2.3.4
 */
object ApiConstants {
    // Mojang
    const val MANIFEST_URL = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
    const val RESOURCES = "https://resources.download.minecraft.net/"
    const val LIBRARIES = "https://libraries.minecraft.net/"
    const val JAVA_RUNTIME_ALL =
        "https://piston-meta.mojang.com/v1/products/java-runtime/2ec0cc96c44e5a76b9c8b7c39df7210883d12871/all.json"
    const val NEWS_URL = "https://launchercontent.mojang.com/v2/javaPatchNotes.json"
    const val NEWS_BASE = "https://launchercontent.mojang.com"

    // Modrinth
    const val MODRINTH_SEARCH = "https://api.modrinth.com/v2/search"
    const val MODRINTH_API = "https://api.modrinth.com/v2"

    /**
     * CurseForge requests go through the Crystal Launcher Cloudflare Worker.
     * The Worker injects the private API key before forwarding to edge.forgecdn.net.
     */
    const val CF_PROXY_BASE = "https://crystal-cf-proxy.vansh123456789-minecraft.workers.dev"

    val CONTENT_TYPES = listOf("modpack", "mod", "shader", "resourcepack", "datapack")

    val EXTERNAL_HOSTS = listOf(
        "modrinth.com", "www.modrinth.com",
        "minecraft.net", "www.minecraft.net",
        "mclo.gs",
        "www.curseforge.com", "curseforge.com"
    )

    const val USER_AGENT = "CrystalLauncher/2.3.4"
}
