package com.tugas.profilesekolah.model

import com.squareup.moshi.Json


data class FounderItem(
    @Json(name = "nama")
    var nama: String?,
    @Json(name = "gambarProfil")
    var gambarProfil: String?,
    @Json(name = "about")
    var about: String?,
    @Json(name = "instagram")
    var instagram: String?,
    @Json(name = "facebook")
    var facebook: String?,
    @Json(name = "twitter")
    var twitter: String?
)
