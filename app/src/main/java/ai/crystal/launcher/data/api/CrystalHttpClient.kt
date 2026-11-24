package ai.crystal.launcher.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * HTTP client that mirrors desktop behaviour:
 * - Sends CrystalLauncher User-Agent
 * - Automatically rewrites forgecdn.net URLs through the Cloudflare Worker proxy
 */
object CrystalHttpClient {

    private val proxyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val url = original.url.toString()

        val rewritten = rewriteIfForgeCdn(url)
        val request = if (rewritten != url) {
            original.newBuilder()
                .url(rewritten)
                .header("User-Agent", ApiConstants.USER_AGENT)
                .build()
        } else {
            original.newBuilder()
                .header("User-Agent", ApiConstants.USER_AGENT)
                .build()
        }
        chain.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(proxyInterceptor)
        .followRedirects(true)
        .build()

    /**
     * Desktop logic:
     * if hostname matches forgecdn.net → CF_PROXY_BASE + "/cdn" + pathname + search
     */
    fun rewriteIfForgeCdn(url: String): String {
        return try {
            val uri = java.net.URI(url)
            val host = uri.host ?: return url
            if (host == "forgecdn.net" || host.endsWith(".forgecdn.net")) {
                val path = uri.rawPath ?: ""
                val query = uri.rawQuery?.let { "?$it" } ?: ""
                "${ApiConstants.CF_PROXY_BASE}/cdn$path$query"
            } else {
                url
            }
        } catch (_: Exception) {
            url
        }
    }

    fun get(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", ApiConstants.USER_AGENT)
            .get()
            .build()
        return client.newCall(request).execute()
    }
}
