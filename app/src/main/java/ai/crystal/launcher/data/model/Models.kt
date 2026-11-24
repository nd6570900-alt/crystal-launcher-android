package ai.crystal.launcher.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Mirrors the desktop DEFAULTS + instances.json structure.
 */
@Serializable
data class LauncherConfig(
    val username: String = "Player",
    val welcomed: Boolean = false,
    val accountType: String = "offline",
    val ram: Int = 3072,
    val java: String = "",
    val version: String = "",
    val width: Int = 1280,
    val height: Int = 720,
    val customRes: Boolean = false,
    val effects: Boolean = true,
    val loader: String = "vanilla",
    val jvmArgs: String = "",
    val hwAccel: Boolean = true,
    val minimizeOnLaunch: Boolean = false,
    val accent: String = "violet",
    val display: String = "dark"
)

@Serializable
data class Instance(
    val id: String,
    val name: String,
    val version: String,
    val loader: String = "vanilla",          // vanilla | fabric | quilt | forge | neoforge
    val loaderVersion: String = "",
    val java: String = "",
    val ram: Int = 3072,
    val jvmArgs: String = "",
    val icon: String = "",
    val created: Long = System.currentTimeMillis(),
    val lastPlayed: Long = 0L
)

@Serializable
data class ModrinthHit(
    val slug: String = "",
    val title: String = "",
    val description: String = "",
    val categories: List<String> = emptyList(),
    @SerialName("client_side") val clientSide: String = "",
    @SerialName("server_side") val serverSide: String = "",
    val downloads: Long = 0,
    val follows: Long = 0,
    @SerialName("icon_url") val iconUrl: String? = null,
    @SerialName("project_type") val projectType: String = "mod",
    val author: String = ""
)

@Serializable
data class ModrinthSearchResponse(
    val hits: List<ModrinthHit> = emptyList(),
    val offset: Int = 0,
    val limit: Int = 20,
    @SerialName("total_hits") val totalHits: Int = 0
)
