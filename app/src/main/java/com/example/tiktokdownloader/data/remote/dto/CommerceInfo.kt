package com.example.tiktokdownloader.data.remote.dto

data class CommerceInfo(
    val adv_promotable: Boolean,
    val auction_ad_invited: Boolean,
    val branded_content_type: Int,
    val organic_log_extra: String,
    val with_comment_filter_words: Boolean
)