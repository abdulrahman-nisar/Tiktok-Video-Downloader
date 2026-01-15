package com.example.tiktokdownloader.util

object UrlValidator {

    /**
     * Returns true if [url] looks like a TikTok URL.
     * Accepts optional protocol and common TikTok subdomains (www, m, vm, vt, t).
     */
    fun isTikTokUrl(url: String?): Boolean {
        if (url.isNullOrBlank()) return false
        return AppConstants.TIKTOK_REGEX.matches(url.trim())
    }

}