package com.schedule.app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout

actual fun createPlatformHttpClient(
    connectTimeoutMs: Long,
    requestTimeoutMs: Long,
): HttpClient = HttpClient(OkHttp) {
    install(HttpTimeout) {
        connectTimeoutMillis = connectTimeoutMs
        requestTimeoutMillis = requestTimeoutMs
    }
    engine {
        // withWindowsTrustFallback — no-op на macOS/Linux (см. её же
        // комментарий в WindowsTrustFallback.kt), реально работает только
        // на Windows.
        config {
            withWindowsTrustFallback()
            // Системный прокси (колледж): явно берём ProxySelector JVM —
            // вместе с java.net.useSystemProxies=true (см. Main.kt) он отдаёт
            // тот же прокси, что и браузер.
            proxySelector(java.net.ProxySelector.getDefault())
            // Фильтры колледжа часто ломают HTTP/2 и IPv6 — идём по HTTP/1.1.
            protocols(listOf(okhttp3.Protocol.HTTP_1_1))
        }
    }
}
