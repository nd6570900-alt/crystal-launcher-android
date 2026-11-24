package ai.crystal.launcher.data.repository

import ai.crystal.launcher.data.api.ApiConstants
import ai.crystal.launcher.data.api.CrystalHttpClient
import ai.crystal.launcher.data.model.ModrinthSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * Ports the desktop Modrinth search + CurseForge-via-proxy logic.
 */
class ContentRepository {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun searchModrinth(
        query: String,
        type: String = "mod",
        limit: Int = 20,
        offset: Int = 0,
        index: String = "downloads"
    ): Result<ModrinthSearchResponse> = withContext(Dispatchers.IO) {
        try {
            val url = ApiConstants.MODRINTH_SEARCH.toHttpUrl().newBuilder()
                .addQueryParameter("query", query)
                .addQueryParameter("limit", limit.toString())
                .addQueryParameter("offset", offset.toString())
                .addQueryParameter("index", index)
                .addQueryParameter("facets", "[[\"project_type:$type\"]]")
                .build()
                .toString()

            val response = CrystalHttpClient.get(url)
            if (!response.isSuccessful) {
                return@withContext Result.failure(
                    Exception("Modrinth search failed: HTTP ${response.code}")
                )
            }
            val body = response.body?.string() ?: return@withContext Result.failure(
                Exception("Empty response")
            )
            val parsed = json.decodeFromString<ModrinthSearchResponse>(body)
            Result.success(parsed)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Returns the proxied download URL for a forgecdn.net link.
     * Same rewrite the desktop main.js performs.
     */
    fun proxiedCdnUrl(originalUrl: String): String {
        return CrystalHttpClient.rewriteIfForgeCdn(originalUrl)
    }
}
