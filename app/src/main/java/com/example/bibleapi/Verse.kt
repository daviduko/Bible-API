package com.example.bibleapi

import com.google.gson.annotations.SerializedName

data class Verse(
    @SerializedName("verseId") val id: Int,
    @SerializedName("verse") val verseText: String
)
